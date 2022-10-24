package com.hit.web.Servlet;

import com.hit.service.UserService;
import com.hit.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/activeServlet")
public class activeServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(activeServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html");
        response.setContentType("text/html;charset=utf-8");
        String code = request.getParameter("code");
        String username = request.getParameter("username");

        logger.info("code=" + MailUtil.CODE);
        logger.info("实际参数为 " + code);

        if(MailUtil.CODE.equals(code)){
            UserService.editUserStatus(username,1);
            response.getWriter().write("<script>alert(\"账号激活成功! 快去登录吧！\");" +
                    "window.location.href='/login.html'</script>");
        }else {
            response.getWriter().write("<script>alert(\"激活参数错误! >_< Please retry!\");" +
                    "window.location.href='/register.html'</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
