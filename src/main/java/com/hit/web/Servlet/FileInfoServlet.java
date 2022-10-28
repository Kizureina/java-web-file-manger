package com.hit.web.Servlet;

import com.alibaba.fastjson.JSON;
import com.hit.service.FileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/fileInfoServlet")
public class FileInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/json;charset=utf-8");
        String userName = (String) session.getAttribute("username");
        String currentIndex = (String) session.getAttribute("index");

        List<com.hit.pojo.File> fileInfo = currentIndex == null ?
                FileService.getFileInfo(new File("F://FMS//" + userName)):
                FileService.getFileInfo(new File("F://FMS//" + userName + "//" + currentIndex));
        String s = JSON.toJSONString(fileInfo);
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
