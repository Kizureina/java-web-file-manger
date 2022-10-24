package com.hit.web.Servlet;

import com.hit.pojo.User;
import com.hit.service.FileService;
import com.hit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Register.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html");
        response.setContentType("text/html;charset=utf-8");
        // 中文编码

        UserService userService = new UserService();
        List<User> users = userService.selectAllUsers();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String check = request.getParameter("check");
        String email = request.getParameter("email");

        if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
            response.getWriter().write("<script>alert(\"邮箱格式错误! >_< Please retry!\");" +
                    "window.location.href='/register.html'</script>");
            return;
        }
        String codeString = Checkcode.CHECK_CODE;

        if (codeString.equals(check)){
            System.out.println("Check it!");
        }else {
            response.getWriter().write("<script>alert(\"验证码错误! >_< Please retry!\");" +
                    "window.location.href='/register.html'</script>");
            return;
        }

        for (User user : users) {
            if (user.getUserName().equals(username)){
                response.getWriter().write("<script>alert(\"Your username had been registered! >_< Please retry!\");" +
                        "window.location.href='/register.html'</script>");
                return;
            }
        }
        int x = userService.insertUser(username,password,0);
        if(x == 1){
            logger.info(username + "注册成功");
            FileService fileService = new FileService(username);
            fileService.createIndex();
            logger.debug("为用户" + username + "新建文件夹成功");

            if (userService.doRegister(username,email)){
                response.getWriter().write("<script>alert(\"Register successful!OVO 快去邮箱激活账户吧！\");" +
                        "window.location.href='/login.html'</script>");
            }else {
                response.getWriter().write("<script>alert(\"出错了！请重试\");" +
                        "window.location.href='/login.html'</script>");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
