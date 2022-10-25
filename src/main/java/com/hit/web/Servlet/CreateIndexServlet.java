package com.hit.web.Servlet;

import com.hit.service.FileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/createIndexServlet")
public class CreateIndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String currentIndex = (String) session.getAttribute("index");
        String userName = (String) session.getAttribute("username");

        String indexName = request.getParameter("indexName");

        if (currentIndex == null){
            FileService fileService = new FileService(userName + "//" + indexName);
            fileService.createIndex();
            return;
        }
        FileService fileService = new FileService(userName + "//" + currentIndex + "//" + indexName);
        fileService.createIndex();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
