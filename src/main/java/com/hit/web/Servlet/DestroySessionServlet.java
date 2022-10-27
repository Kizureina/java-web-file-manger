package com.hit.web.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author Yoruko
 */
@WebServlet("/destroySessionServlet")
public class DestroySessionServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DestroySessionServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html");
        response.setContentType("text/html;charset=utf-8");

        //false代表：不创建session对象，只是从request中获取。
        HttpSession session = request.getSession(false);
        if(session.getAttribute("username") == null){
            logger.info("已注销过！");
            return;
        }
        session.removeAttribute("username");
        if (session.getAttribute("index") != null){
            session.removeAttribute("index");
        }

        logger.info("账号注销成功！");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
