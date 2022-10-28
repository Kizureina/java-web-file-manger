package com.hit.web.Servlet;

import com.alibaba.fastjson.JSON;
import com.hit.pojo.File;
import com.hit.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/getSubFilesServlet")
public class GetSubFilesServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(GetSubFilesServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String currentIndex = (String) session.getAttribute("index");

        String indexName = new String(request.getParameter("indexName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        FileService.CURRENT_FOLDER.add(indexName);
        List<File> fileInfo;
        if (currentIndex == null){
            session.setAttribute("index",indexName);
            fileInfo = FileService.getFileInfo(new java.io.File("F://FMS//" + userName + "//" + indexName));
            if (fileInfo == null){
                response.getWriter().write(JSON.toJSONString(0));
                return;
            }
        } else {
            session.setAttribute("index",currentIndex + "//" + indexName);

            fileInfo = FileService.getFileInfo(new java.io.File("F://FMS//" + userName + "//" + currentIndex + "//" + indexName));
            if(fileInfo == null){
                response.getWriter().write(JSON.toJSONString(0));
                return;
            }
        }
        String s = JSON.toJSONString(fileInfo);
        response.getWriter().write(s);
        logger.info("进入子目录" + indexName);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
