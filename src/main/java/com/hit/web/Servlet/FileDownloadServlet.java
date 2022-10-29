package com.hit.web.Servlet;

import com.alibaba.fastjson.JSON;
import com.hit.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/fileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FileDownloadServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/multipart/form-data;charset=utf-8");
        // 用于文件传输
        String fileName = new String(request.getParameter("fileName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName,"UTF-8"));
        // 强制浏览器下载文件，解决中文乱码
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String currentIndex = (String) session.getAttribute("index");

        File file = currentIndex == null ?
                new File("F://FMS//" + userName + "//" + fileName):
                new File("F://FMS//" + userName + "//" + currentIndex + "//" + fileName);
        if (file.isDirectory()){
            String s = JSON.toJSONString(false);
            response.getWriter().write(s);
            logger.error("下载对象不可为文件夹" + fileName);
            return;
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            logger.info(fileName + "文件下载成功");
        } catch (FileNotFoundException e) {
            logger.error(fileName + "文件下载失败！");
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
