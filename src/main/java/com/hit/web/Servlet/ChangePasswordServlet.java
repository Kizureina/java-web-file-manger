package com.hit.web.Servlet;

import com.alibaba.fastjson.JSON;
import com.hit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/changePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ChangePasswordServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = new String(request.getParameter("password").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        UserService userService = new UserService();
        if(userService.changePasswordByName(username,password)){
            String s = JSON.toJSONString(true);
            response.getWriter().write(s);
            logger.info(username + "修改密码成功");
        }else{
            String s = JSON.toJSONString(false);
            response.getWriter().write(s);
            logger.error(username + "修改密码失败");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
