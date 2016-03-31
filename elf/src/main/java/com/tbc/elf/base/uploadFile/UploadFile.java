package com.tbc.elf.base.uploadFile;


import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class UploadFile {

    //处理结果
    private String result;
    //处理结果详情，如果是错误是异常
    private Object detail;
    //文件ID
    private String fileId;
    //文件
    private String fileName;
    //文件类型
    private String fileType = FileType.fs.name();
    //文件结尾
    private String suffix;
    //文件对象
    private MultipartFile file;
    //返回类型
    private String responseFormat = ResponseFormat.json.name();
    //文件大小
    private long fileSize;
    //文件上传时间
    private Date uploadTime = new Date();
    //文件上传花费时间
    private long costTime;
    //文件内容类型
    private String contentType;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public enum Result {
        success, failed
    }

    public enum FileType {
        fs, sys, course
    }

    public enum ResponseFormat {
        json, html, xml
    }
}