package com.hit.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JavaHomework {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //����Ҫ��ȡ��Ŀ���ļ���
        String fileName = scanner.next();
        //��ȡ��Ŀ¼
        File base = new File(Objects.requireNonNull(JavaHomework.class.getResource("")).getPath()).getParentFile();
        //��������ļ���ʵ����File�����
        File file = new File(base+File.separator+fileName);
        //���ļ��ж�ȡ���ݣ������ذ������ݵ�List����
        List<Integer> data = readFromFile(file);
        //��������а�����int���ݵ�����
        System.out.println(data.size());
    }
    //�����ӿ�����
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
        //��ɷ�����ʵ�֣����ύ�ò��ִ���
}
