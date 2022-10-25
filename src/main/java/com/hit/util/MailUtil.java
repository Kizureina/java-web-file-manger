package com.hit.util;

import com.hit.web.Servlet.Register;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Yoruko
 */
public class MailUtil implements Runnable {
    public static String CODE;
    private String email;
    private final String username;
    private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    public MailUtil(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void run() {
        // 1.创建连接对象javax.mail.Session
        // 2.创建邮件对象 javax.mail.Message
        // 3.发送一封激活邮件
        String from = "2932347622@qq.com";
        String host = "smtp.qq.com";

        Properties properties = System.getProperties();
        // 获取系统属性
        properties.setProperty("mail.smtp.host", host);
        // 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");
        // 打开认证

        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2932347622@qq.com", "yesiqsguvpxfdfgh");
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1>" +
                    "<h3><a href='http://localhost/activeServlet?username=" + username + "&code="
                    + CODE + "'>点击这里"
                    + "</a></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            logger.info("向" + email + "邮件成功发送!");
        } catch (Exception e) {
            logger.error("向" + email + "邮件发送失败!");
            e.printStackTrace();
        }
    }
}
