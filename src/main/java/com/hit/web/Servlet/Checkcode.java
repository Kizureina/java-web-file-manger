package com.hit.web.Servlet;

import com.hit.util.CheckCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Yoruko
 */

@WebServlet("/checkcode")
public class Checkcode extends HttpServlet {
    public static String CHECK_CODE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String t = request.getParameter("t");
//        System.out.println("time : " + t);
        CHECK_CODE = getCodeString();
//        System.out.println(CHECK_CODE);
        // 生成图片
        BufferedImage checkImg = CheckCodeUtil.getCheckImg(CHECK_CODE);
        // 将画布显示在浏览器中
        ImageIO.write(checkImg, "jpg", response.getOutputStream());
    }

    public static String getCodeString() {
        // 准备数据
        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random r = new Random();
        // 声明一个变量 保存验证码
        StringBuilder code = new StringBuilder();
        // 书写4个随机字符
        for (int i = 0; i < 4; i++) {
            // 将新的字符 保存到验证码中
            code.append(data.charAt(r.nextInt(data.length())));
        }
        return code.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
