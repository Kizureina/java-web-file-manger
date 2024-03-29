package com.hit.web.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.hit.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userInfo")
public class UserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");

        String userStr = JSONObject.toJSONString(userName);
        response.getWriter().write(userStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
