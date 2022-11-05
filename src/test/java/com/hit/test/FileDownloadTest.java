package com.hit.test;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDownloadTest {
    private static int position = -1;

    @Test
    public void test() {
        // Դ�ļ���Ŀ���ļ�
        File sourceFile = new File("F:/", "test.txt");
        File targetFile = new File("E:/", "test.txt");
        // ���������
        FileInputStream fis = null;
        FileOutputStream fos = null;
        // ���ݻ�����
        byte[] buf = new byte[1];

        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            // ���ݶ�д
            while (fis.read(buf) != -1) {
                fos.write(buf);
                // ���Ѿ��ϴ���3�ֽڵ��ļ�����ʱ,ģ�������ж��ˣ��׳��쳣
                if (targetFile.length() == 3) {
                    position = 3;
                    throw new FileAccessException();
                }
            }

        } catch (FileAccessException e) {
            keepGoing(sourceFile, targetFile, position);
        } catch (FileNotFoundException e) {
            System.out.println("ָ���ļ�������");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // �ر����������
                if (fis != null)
                    fis.close();

                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void keepGoing(File source, File target, int position) {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            RandomAccessFile readFile = new RandomAccessFile(source, "rw");
            RandomAccessFile writeFile = new RandomAccessFile(target, "rw");

            readFile.seek(position);
            writeFile.seek(position);

            // ���ݻ�����
            byte[] buf = new byte[1];
            // ���ݶ�д
            while (readFile.read(buf) != -1) {
                writeFile.write(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class FileAccessException extends Exception {
}