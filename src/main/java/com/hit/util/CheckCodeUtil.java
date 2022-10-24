package com.hit.util;
import java.awt.*;

import java.awt.image.BufferedImage;

import java.util.Random;

public class CheckCodeUtil {
    public static BufferedImage getCheckImg(String code) {
        Random r = new Random();
        // 创建画布
        int width = 120;
        int height = 40;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获得画笔
        Graphics g = bufferedImage.getGraphics();
        // 填充背景颜色
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        // 绘制边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 28));
        // 绘制干扰线
        for (int i = 0; i < 6; i++) {
        // 设置随机颜色
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width),
                    r.nextInt(height));
        }
        // 书写4个字符
        for (int i = 0; i < 4; i++) {
            String ch = code.charAt(i) + "";
            // 设置随机颜色
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawString(ch, 10 + i * 28, 30);
        }
        return bufferedImage;
    }
}