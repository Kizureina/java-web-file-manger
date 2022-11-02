package com.hit.web.Servlet;

import com.hit.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/createIndexServlet")
public class CreateIndexServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(CreateIndexServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String currentIndex = (String) session.getAttribute("index");
        String userName = (String) session.getAttribute("username");

        String indexName = new String(request.getParameter("indexName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        // 解决中文乱码
        FileService fileService = currentIndex == null ? new FileService(userName + "/" + indexName): new FileService(userName + "/" + currentIndex + "/" + indexName);
        try {
            fileService.createIndex();
            logger.info(indexName + "文件夹创建成功");
        } catch (Exception e) {
            logger.error(indexName + "文件夹创建失败！");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
