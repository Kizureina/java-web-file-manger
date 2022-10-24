package com.hit.web.Listener;

import com.hit.service.UserService;
import com.hit.web.Servlet.Login;
import com.mysql.cj.conf.PropertyKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@WebListener
public class SessionListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);
    private static final UserService userService = new UserService();

    public SessionListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
//        HttpSession session = se.getSession();
//        String username = (String)session.getAttribute("username");
//        userService.editUserStatus(username,0);
//        logger.info("用户" + username + "超时退出");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
