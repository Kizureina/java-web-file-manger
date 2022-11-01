package com.hit.web.Servlet;

import com.hit.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Yoruko
 */
@WebServlet("/renameFileServlet")
public class RenameFileServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RenameFileServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");
        String userName = (String) session.getAttribute("username");
        String currentIndex = (String) session.getAttribute("index");

        String oldName = new String(request.getParameter("oldName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String newName = new String(request.getParameter("newName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        FileService fileService = currentIndex == null ?
                new FileService(FileService.ROOT_PATH + userName + "//"):
                new FileService(FileService.ROOT_PATH + userName + "//" + currentIndex + "//");
        if(fileService.renameFile(oldName, newName)){
            logger.info(oldName + "文件重命名成功，新文件名为" + newName);
            response.getWriter().write("true");
        }else {
            logger.info(oldName + "文件重命名失败");
            response.getWriter().write("false");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
