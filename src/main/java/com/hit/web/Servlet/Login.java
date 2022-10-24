package com.hit.web.Servlet;

import com.hit.pojo.User;
import com.hit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Yoruko
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html");
        response.setContentType("text/html;charset=utf-8");

        UserService userService = new UserService();
        List<User> users = userService.selectAllUsers();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassWord().equals(password)){
                int status = userService.selectUserStatusByName(username);
                if(status == 0){
                    response.getWriter().write("<script>alert(\"登录失败！账号未激活！\");window.location.href='/login.html'</script>");
                    logger.error(username + "登录失败，账号未激活");
                    return;
                }
                HttpSession session = request.getSession();
                session.setAttribute("username",user.getUserName());
                logger.info(username + "登录成功");
                response.getWriter().write("<script>alert(\"登录成功！\");window.location.href='/FMSindex.html'</script>");
                return;
            }
        }
        logger.error(username + "登录失败！");
        response.getWriter().write("<script>alert(\"登录失败! >_< 请重试!\");window.location.href='/login.html'</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
