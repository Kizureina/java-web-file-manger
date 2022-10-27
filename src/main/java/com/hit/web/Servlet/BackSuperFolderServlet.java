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

/**
 * @author Yoruko
 */
@WebServlet("/backSuperFolderServlet")
public class BackSuperFolderServlet extends HttpServlet {
    public static int flag = 0;
    private static final Logger logger = LoggerFactory.getLogger(BackSuperFolderServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(flag != 0){
            return;
        }
        String currentFolder = FileService.CURRENT_FOLDER.get(FileService.CURRENT_FOLDER.size() - 1);
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String currentIndex = (String) session.getAttribute("index");
        response.setContentType("text/html;charset=utf-8");

        currentIndex = currentIndex.replace("//" + currentFolder,"");
        session.setAttribute("index",currentIndex);
        List<File> fileInfo;
        if (currentIndex.equals(currentFolder)){
            fileInfo = FileService.getFileInfo(new java.io.File("F://FMS//" + userName));
        }else{
            fileInfo = FileService.getFileInfo(new java.io.File("F://FMS//" + userName + "//" + currentIndex));
        }
        String s = JSON.toJSONString(fileInfo);
        response.getWriter().write(s);
        logger.info("返回了上一级目录" + currentFolder);
        FileService.CURRENT_FOLDER.remove(currentFolder);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
