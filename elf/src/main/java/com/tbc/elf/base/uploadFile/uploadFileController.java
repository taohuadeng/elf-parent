package com.tbc.elf.base.uploadFile;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Resource
    MultipartResolver multipartResolver;
    private static final Gson GSON = new Gson();

    private String filePath = "/web/file/";

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/uploadFile")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadFile uploadFile = new UploadFile();
        String fileId = UUID.randomUUID().toString().replace("-", "");
        response.setContentType("text/html;charset=UTF-8");
        try {
            ServletRequestDataBinder binder = new ServletRequestDataBinder(uploadFile);
            binder.bind(request);
            MultipartFile file = uploadFile.getFile();
            if (file != null) {
                processUploadFile(fileId, uploadFile, file);
                uploadFile.setFile(null);
                response.getWriter().print(GSON.toJson(uploadFile));
                return;
            }

            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator<String> fileNames = multipartRequest.getFileNames();
                if (fileNames == null || !fileNames.hasNext()) {
                    uploadFile.setResult(UploadFile.Result.failed.name());
                    uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
                    uploadFile.setDetail("NOT UPLOAD FILE");
                    uploadFile.setFile(null);
                    response.getWriter().print(GSON.toJson(uploadFile));
                    return;
                }

                String fileName = fileNames.next();
                if (fileNames.hasNext()) {
                    uploadFile.setResult(UploadFile.Result.failed.name());
                    uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
                    uploadFile.setDetail("NOT UPLOAD FILE");
                    uploadFile.setFile(null);
                    response.getWriter().print(GSON.toJson(uploadFile));
                    return;
                }

                file = multipartRequest.getFile(fileName);
                if (file == null) {
                    uploadFile.setResult(UploadFile.Result.failed.name());
                    uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
                    uploadFile.setDetail("NOT UPLOAD FILE");
                    uploadFile.setFile(null);
                    response.getWriter().print(GSON.toJson(uploadFile));
                    return;
                }

                processUploadFile(fileId, uploadFile, file);
                uploadFile.setFile(null);
                response.getWriter().print(GSON.toJson(uploadFile));
                return;
            }

            IOUtils.copy(request.getInputStream(), new FileOutputStream(filePath + fileId + "/data"));
            uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
            uploadFile.setFileId(fileId);
            uploadFile.setResult(UploadFile.Result.success.name());
            uploadFile.setContentType(request.getContentType());
            IOUtils.write(GSON.toJson(uploadFile), new FileOutputStream(new File(filePath + fileId + "metadata")));
        } catch (Exception e) {
            uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
            uploadFile.setResult(UploadFile.Result.failed.name());
            uploadFile.setDetail(e);
        }

        uploadFile.setFile(null);
        response.getWriter().print(GSON.toJson(uploadFile));
    }

    private void processUploadFile(String fileId, UploadFile uploadFile, MultipartFile file) throws Exception {
        uploadFile.setContentType(file.getContentType());
        String filename = file.getOriginalFilename();
        uploadFile.setFileName(filename);
        uploadFile.setFileSize(file.getSize());
        uploadFile.setSuffix(filename.contains(".")
                ? filename.substring(filename.indexOf(".") + 1) : null);
        File parent = new File(filePath + fileId + "/");
        if (!parent.mkdirs()) {
            throw new IOException("Create directory[" + filePath + fileId + "/] failed!");
        }

        IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(parent, "data")));
        uploadFile.setCostTime(System.currentTimeMillis() - uploadFile.getUploadTime().getTime());
        uploadFile.setFileId(fileId);
        uploadFile.setResult(UploadFile.Result.success.name());
        IOUtils.write(GSON.toJson(uploadFile), new FileOutputStream(new File(parent, "metadata")));
    }

    @RequestMapping(value = "/getFile/{fileId}")
    public void getFile(@PathVariable("fileId") String fileId, HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        //response.setContentType("multipart/form-data");
        //response.setHeader("Content-Disposition", "attachment;fileName=" + fileId);
        String metadata = IOUtils.toString(new FileInputStream(filePath + fileId + "/metadata"));
        UploadFile uploadFile = GSON.fromJson(metadata, UploadFile.class);
        response.setContentType(uploadFile.getContentType());
        response.setHeader("Content-Disposition", "attachment;fileName=" + uploadFile.getFileName());
        IOUtils.copy(new FileInputStream(filePath + fileId + "/data"), response.getWriter());
    }

}
