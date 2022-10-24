package com.hit.web.Servlet;

import com.alibaba.fastjson.JSON;
import com.hit.service.FileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/fileInfoServlet")
public class FileInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");

        List<com.hit.pojo.File> fileInfo = FileService.getFileInfo(new File("F:\\FMS\\" + userName));
        String s = JSON.toJSONString(fileInfo);
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
