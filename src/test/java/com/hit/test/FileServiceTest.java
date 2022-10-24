package com.hit.test;

import com.hit.pojo.File;
import com.hit.pojo.User;
import com.hit.service.FileService;
import com.hit.service.UserService;
import org.junit.Test;

import java.util.List;

public class FileServiceTest {
    @Test
    public void testfile(){
        UserService userService = new UserService();
        List<User> users = userService.selectAllUsers();

        String userName = users.get(0).getUserName();

        FileService fileService = new FileService(userName);
        fileService.createIndex();
    }
    @Test
    public void testDigui(){
//        FileService.fileRecursion(new File("F://FMS"));
    }
    @Test
    public void testGetFileInfo(){
        List<File> fileInfo = FileService.getFileInfo(new java.io.File("F:\\FMS\\SerenKawaiii"));
        System.out.println(fileInfo);
        //[File{filesize=0, fileName='202205工程实践', editTime=Mon Oct 24 23:56:13 CST 2022},
//        File{filesize=3070, fileName='数字孪生技术1.txt', editTime=Wed Oct 12 22:44:53 CST 2022},
//        File{filesize=0, fileName='数据结构', editTime=Mon Oct 24 23:56:13 CST 2022},
//        File{filesize=0, fileName='模电', editTime=Mon Oct 24 23:56:13 CST 2022}]
    }
}

