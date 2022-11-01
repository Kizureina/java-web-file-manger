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
    private static final Logger logger = LoggerFactory.getLogger(BackSuperFolderServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentFolder = FileService.CURRENT_FOLDER.get(FileService.CURRENT_FOLDER.size() - 1);
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        if(currentFolder.equals(userName)){
            String s = JSON.toJSONString(null);
            response.getWriter().write(s);
            return;
        }
        String currentIndex = (String) session.getAttribute("index");
        response.setContentType("text/json;charset=utf-8");

        List<File> fileInfo;
        if(currentIndex.equals(currentFolder)){
            FileService.CURRENT_FOLDER.clear();
            FileService.CURRENT_FOLDER.add(userName);
            session.removeAttribute("index");
            try {
                fileInfo = FileService.getFileInfo(new java.io.File(FileService.ROOT_PATH + userName));
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write(JSON.toJSONString(0));
                return;
            }
        }else {
            String nowIndex = currentIndex.replace("//" + currentFolder,"");
            session.setAttribute("index",nowIndex);
            try {
                fileInfo = FileService.getFileInfo(new java.io.File(FileService.ROOT_PATH + userName + "//" + nowIndex));
            } catch (Exception e) {
                e.printStackTrace();
                String s = JSON.toJSONString(0);
                response.getWriter().write(s);
                return;
            }
        }
        String s = JSON.toJSONString(fileInfo);
        response.getWriter().write(s);
        logger.info("返回了" + currentIndex + "的上一级目录");
        FileService.CURRENT_FOLDER.remove(currentFolder);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
