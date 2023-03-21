package com.hit.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JavaHomework {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //输入要读取的目标文件名
        String fileName = scanner.next();
        //获取根目录
        File base = new File(Objects.requireNonNull(JavaHomework.class.getResource("")).getPath()).getParentFile();
        //用输入的文件名实例化File类对象
        File file = new File(base+File.separator+fileName);
        //从文件中读取数据，并返回包含数据的List集合
        List<Integer> data = readFromFile(file);
        //输出集合中包含的int数据的数量
        System.out.println(data.size());
    }
    //方法接口声明
    public static List<Integer> readFromFile(File file){
        DataInputStream dataInputStream = null;
        List<Integer> list = new ArrayList<>();
        try {
            dataInputStream = new DataInputStream(new FileInputStream(file));
            int num;
            while (dataInputStream.readInt() != -1) {
                num = dataInputStream.read();
                list.add(num);
            }
        } catch (Exception e) {
            return list;
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
        //完成方法的实现，并提交该部分代码
}
