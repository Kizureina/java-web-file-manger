package com.hit.pojo;

import java.util.Date;

public class File{
    private long filesize;
    private String fileName;
    private Date editTime;
    public double getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    @Override
    public String toString() {
        return "File{" +
                "filesize=" + filesize +
                ", fileName='" + fileName + '\'' +
                ", editTime=" + editTime +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public File() {
    }

    public File(long filesize, String fileName, Date editTime) {
        this.filesize = filesize;
        this.fileName = fileName;
        this.editTime = editTime;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}
