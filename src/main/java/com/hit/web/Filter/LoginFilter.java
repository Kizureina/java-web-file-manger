package com.hit.web.Filter;

import com.hit.web.Servlet.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
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
        String[] urls = {"/login.html","/css/","/img/","/js/","/login","/register.html","/register","/selectAllUser","/checkcode","/activeServlet"};
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
            chain.doFilter(request, response);
        }else {
            req.setAttribute("login_msg","请登录！");
            req.getRequestDispatcher("/login.html").forward(req,response);
        }
    }
}
