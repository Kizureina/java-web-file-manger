package com.hit.test;

import com.hit.pojo.User;
import com.hit.service.FileService;
import com.hit.service.UserService;
import org.junit.Test;

import java.io.File;
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
        FileService.fileRecursion(new File("F://FMS"));
    }
}
