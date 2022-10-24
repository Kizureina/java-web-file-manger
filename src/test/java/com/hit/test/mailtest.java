package com.hit.test;

import com.hit.service.UserService;
import com.hit.util.MailUtil;
import org.junit.Test;

public class mailtest {
    @Test
    public void mailTest1(){
        MailUtil.CODE = "dwawawaaw";
        MailUtil mailUtil = new MailUtil("houchangkun@gmail.com", "nagisa");
        new Thread(mailUtil).start();
    }
    @Test
    public void sqlTest(){
        UserService.editUserStatus("Shirou",1);
    }
    @Test
    public void sqlTest2(){
        UserService userService = new UserService();
        System.out.println(userService.selectUserStatusByName("SerenKawaii"));
    }
}
