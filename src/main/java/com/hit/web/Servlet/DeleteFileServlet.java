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

@WebServlet("/deleteFileServlet")
public class DeleteFileServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DeleteFileServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");
        String userName = (String) session.getAttribute("username");
        String currentIndex = (String) session.getAttribute("index");

        String indexName = new String(request.getParameter("indexName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        FileService fileService = currentIndex == null ? new FileService(userName + "/" + indexName): new FileService(userName + "/" + currentIndex + "/" + indexName);

        if (fileService.deleteFile()){
            logger.info(indexName + "文件删除成功");
            response.getWriter().write("true");
        }else{
            logger.error(indexName + "删除失败！不可删除非空文件夹");
            response.getWriter().write("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
