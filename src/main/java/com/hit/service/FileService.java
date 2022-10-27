package com.hit.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileService {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileService() {
    }

    public FileService(String path) {
        this.path = path;
    }

    public static List<com.hit.pojo.File> getFileInfo(File file){
        List<com.hit.pojo.File> fileList = new ArrayList<>();
        //1、判断传入的是否是目录
        if(!file.isDirectory()){
            //不是目录直接退出
            return null;
        }
        //已经确保了传入的file是目录
        File[] files = file.listFiles();
        //遍历files
        assert files != null;
        for (File f: files) {
            //如果该目录下文件还是个文件夹就再进行递归遍历其子目录
            if(f.isDirectory()){
                long lastModified = f.lastModified();
                Date date = new Date(lastModified);
                com.hit.pojo.File file1 = new com.hit.pojo.File(0,f.getName(), date);
                fileList.add(file1);
            }else {
                long lastModified = f.lastModified();
                Date date = new Date(lastModified);
                com.hit.pojo.File file1 = new com.hit.pojo.File(f.length(),f.getName(), date);
                fileList.add(file1);
            }
        }
        return fileList;
    }
    public void createIndex(){
        Path path = Paths.get("F://FMS//" + getPath());
        try {
            Path pathCreate = Files.createDirectory(path);
            System.out.println(pathCreate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteFile(){
        try {
            Path path1 = Paths.get("F://FMS//" + path);
            if (Files.exists(path1)){
                Files.delete(path1);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public boolean renameFile(String oldName, String newName){
        try {
            File newFile = new File(path + newName);
            if (newFile.exists()) {
                //  确保新的文件名不存在
                throw new java.io.IOException("file exists");
            }
            return new File(path + oldName).renameTo(new File(path + newName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
