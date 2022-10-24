package com.hit.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static void fileRecursion(File file){
        //1、判断传入的是否是目录
        if(!file.isDirectory()){
            //不是目录直接退出
            return;
        }
        //已经确保了传入的file是目录
        File[] files = file.listFiles();
        //遍历files
        assert files != null;
        for (File f: files) {
            //如果该目录下文件还是个文件夹就再进行递归遍历其子目录
            if(f.isDirectory()){
                //递归
                fileRecursion(f);
            }else {
                //如果该目录下文件是个文件，则打印对应的名字
                System.out.println(f.getName());
            }

        }
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
}
