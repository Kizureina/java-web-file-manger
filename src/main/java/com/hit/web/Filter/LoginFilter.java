package com.hit.web.Filter;

import com.hit.web.Servlet.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String[] urls = {"/login.html","/css/","/img/","/js/","/login","/register.html","/register","/selectAllUser","/checkcode","/activeServlet","/destroySessionServlet"};
        //放行与登录相关的资源
        String url = req.getRequestURL().toString();
        for (String s : urls) {
            if(url.contains(s)){
                chain.doFilter(req,response);
                return;
            }
        }
        HttpSession session = req.getSession();
        Object user = session.getAttribute("username");

//        logger.info("当前登录账户为" + user);
        if (user != null){
            String origin = req.getHeader("Origin");

            resp.setHeader("Access-Control-Allow-Origin", origin);
            logger.info(origin);
            // 允许带有cookie访问
            resp.setHeader("Access-Control-Allow-Credentials", "true");

            // 告诉浏览器允许跨域访问的方法
            resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");

            // 告诉浏览器允许带有所有的请求头访问
            resp.setHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, Content-Type, Accept, x-access-token");

            // 告诉浏览器缓存OPTIONS预检请求1小时,避免非简单请求每次发送预检请求,提升性能
            resp.setHeader("Access-Control-Max-Age", "3600");


            chain.doFilter(req, resp);

        }else {
            req.setAttribute("login_msg","请登录！");
            req.getRequestDispatcher("/login.html").forward(req,response);
        }
    }
}
