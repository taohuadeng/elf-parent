package com.tbc.elf.base.uploadFile;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

@RequestMapping("/fileUpload")
@Controller
public class uploadFileController {

    private static final Gson GSON = new Gson();
    public static final String FILE_UPLOAD_DATA = "data";
    public static final String FILE_UPLOAD_METADATA = "metadata";
    public static final String FILE_SEPARATOR = "/";

    @Resource
    private MultipartResolver multipartResolver;
    @Value("${fileUpload.filePathPrefix}")
    private String filePath;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/handleMaxUploadSizeException")
    public UploadFile handleMaxUploadSizeException() {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setResult(UploadFile.Result.FAILED.name());
        uploadFile.setErrorType(UploadFile.ErrorType.MAX_UPLOAD_SIZE.name());
        return uploadFile;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public UploadFile uploadFile(HttpServletRequest request) {
        UploadFile uploadFile = new UploadFile();
        String fileId = UUID.randomUUID().toString().replace("-", "");
        try {
            new ServletRequestDataBinder(uploadFile).bind(request);
            MultipartFile file = uploadFile.getFile();
            if (file != null) {
                processUploadFile(fileId, uploadFile, file);
                uploadFile.setFile(null);
                return uploadFile;
            }

            if (!multipartResolver.isMultipart(request)) {
                uploadFile.setResult(UploadFile.Result.FAILED.name());
                uploadFile.setErrorType(UploadFile.ErrorType.UPLOAD_STYLE_ERROR.name());
                return uploadFile;
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            if (fileNames == null || !fileNames.hasNext()) {
                uploadFile.setResult(UploadFile.Result.FAILED.name());
                uploadFile.setErrorType(UploadFile.ErrorType.NO_FILE_UPLOADED.name());
                return uploadFile;
            }

            String fileName = fileNames.next();
            if (fileNames.hasNext()) {
                uploadFile.setResult(UploadFile.Result.FAILED.name());
                uploadFile.setErrorType(UploadFile.ErrorType.NOT_SUPPORT_MULTI_FILE.name());
                return uploadFile;
            }

            file = multipartRequest.getFile(fileName);
            if (file == null) {
                uploadFile.setResult(UploadFile.Result.FAILED.name());
                uploadFile.setErrorType(UploadFile.ErrorType.NO_FILE_UPLOADED.name());
                return uploadFile;
            }

            processUploadFile(fileId, uploadFile, file);
            return uploadFile;
        } catch (Exception e) {
            uploadFile.setResult(UploadFile.Result.FAILED.name());
            uploadFile.setErrorType(UploadFile.ErrorType.UN_KNOW_ERROR.name());
            uploadFile.setDetail(e);
        }

        return uploadFile;
    }

    private void processUploadFile(String fileId, UploadFile uploadFile, MultipartFile file) throws Exception {
        uploadFile.setContentType(file.getContentType());
        String filename = file.getOriginalFilename();
        uploadFile.setFileName(filename);
        uploadFile.setFileSize(file.getSize());
        uploadFile.setSuffix(filename.contains(".")
                ? filename.substring(filename.indexOf(".") + 1) : null);
        File parent = new File(filePath + fileId + FILE_SEPARATOR);
        if (!parent.mkdirs()) {
            throw new IOException("Create directory[" + filePath + fileId + "/] failed!");
        }

        IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(parent, FILE_UPLOAD_DATA)));
        uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
        uploadFile.setFileId(fileId);
        uploadFile.setResult(UploadFile.Result.SUCCESS.name());
        IOUtils.write(GSON.toJson(uploadFile), new FileOutputStream(new File(parent, FILE_UPLOAD_METADATA)));
    }

    @RequestMapping(value = "/getFile/{fileId}")
    public void getFile(@PathVariable("fileId") String fileId, HttpServletResponse response) throws Exception {
        String parent = filePath + fileId + FILE_SEPARATOR;
        String metadata = IOUtils.toString(new FileInputStream(parent + FILE_UPLOAD_METADATA));
        UploadFile uploadFile = GSON.fromJson(metadata, UploadFile.class);
        response.setCharacterEncoding("utf-8");
        response.setContentType(uploadFile.getContentType());
        response.setHeader("Content-Disposition", "attachment;fileName=" + uploadFile.getFileName());
        IOUtils.copy(new FileInputStream(parent + FILE_UPLOAD_DATA), response.getWriter());
    }

}
