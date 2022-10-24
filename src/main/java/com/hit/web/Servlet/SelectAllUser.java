package com.hit.web.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.hit.pojo.User;
import com.hit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectAllUser")
public class SelectAllUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> users = userService.selectAllUsers();

        String username = request.getParameter("username");
        for (User user : users) {
            if (user.getUserName().equals(username)){
                response.getWriter().write("yes");
                return;
            }
        }
        response.getWriter().write("no");
//        List<String> usernames = new ArrayList<>();
//        for (User user : users) {
//            usernames.add(user.getUserName());
//        }
//        String userStr = JSONObject.toJSONString(usernames);
//        response.getWriter().write(userStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
