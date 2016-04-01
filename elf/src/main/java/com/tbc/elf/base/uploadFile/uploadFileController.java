package com.tbc.elf.base.uploadFile;

import com.google.gson.Gson;
import com.tbc.elf.base.util.UUIDGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
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
import java.util.*;

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
    public UploadResult handleMaxUploadSizeException() {
        UploadResult uploadResult = new UploadResult();
        uploadResult.setResult(UploadResult.Result.FAILED.name());
        uploadResult.setErrorType(UploadResult.ErrorType.MAX_UPLOAD_SIZE.name());
        return uploadResult;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public UploadResult uploadFile(HttpServletRequest request) {
        UploadResult uploadResult = new UploadResult();
        if (!multipartResolver.isMultipart(request)) {
            uploadResult.setResult(UploadResult.Result.FAILED.name());
            uploadResult.setErrorType(UploadResult.ErrorType.UPLOAD_STYLE_ERROR.name());
            return uploadResult;
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        if (fileNames == null || !fileNames.hasNext()) {
            uploadResult.setResult(UploadResult.Result.FAILED.name());
            uploadResult.setErrorType(UploadResult.ErrorType.NO_FILE_UPLOADED.name());
            return uploadResult;
        }

        new ServletRequestDataBinder(uploadResult).bind(request);
        List<UploadFile> uploadFiles = new ArrayList<UploadFile>();
        uploadResult.setFiles(uploadFiles);
        List<String> fileIds = new ArrayList<String>();
        String module = uploadResult.getModule();
        try {
            String fileId = UUIDGenerator.uuid();
            fileIds.add(fileId);
            while (fileNames.hasNext()) {
                MultipartFile file = multipartRequest.getFile(fileNames.next());
                if (file == null) {
                    deleteFile(fileIds, module);
                    uploadResult.setResult(UploadResult.Result.FAILED.name());
                    uploadResult.setErrorType(UploadResult.ErrorType.UPLOAD_FILE_NOE_EXIST.name());
                    return uploadResult;
                }

                uploadFiles.add(uploadFile(fileId, module, file));
            }

            uploadResult.setCostTime(new Date().getTime() - uploadResult.getUploadTime().getTime());
            uploadResult.setResult(UploadResult.Result.SUCCESS.name());
        } catch (Exception e) {
            uploadResult.setResult(UploadResult.Result.FAILED.name());
            uploadResult.setErrorType(UploadResult.ErrorType.UN_KNOW_ERROR.name());
            uploadResult.setDetail(e);
            deleteFile(fileIds, module);
        }

        return uploadResult;
    }

    private UploadFile uploadFile(String fileId, String module, MultipartFile file) throws Exception {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileId(fileId);
        String filename = file.getOriginalFilename();
        uploadFile.setFileName(filename);
        uploadFile.setModule(module);
        uploadFile.setSuffix(filename.contains(".") ? filename.substring(filename.indexOf(".") + 1) : null);
        uploadFile.setFileSize(file.getSize());
        uploadFile.setContentType(file.getContentType());
        //uploadFile.setOwner(上传人);

        File parent = new File(filePath + module + FILE_SEPARATOR + fileId + FILE_SEPARATOR);
        if (!parent.mkdirs()) {
            throw new IOException("Create directory[" + parent.getAbsoluteFile() + "] failed!");
        }
        IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(parent, FILE_UPLOAD_DATA)));
        uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
        IOUtils.write(GSON.toJson(uploadFile), new FileOutputStream(new File(parent, FILE_UPLOAD_METADATA)));

        return uploadFile;
    }

    private void deleteFile(List<String> fileIds, String module) {
        if (CollectionUtils.isEmpty(fileIds)) {
            return;
        }

        for (String fileId : fileIds) {
            File file = new File(filePath + module + FILE_SEPARATOR + fileId + FILE_SEPARATOR);
            if (!file.exists()) {
                continue;
            }

            try {
                FileUtils.forceDelete(file);
            } catch (Exception e) {
                //
            }
        }
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
