package io.haobin.recursion;

import java.io.File;

/**
 * @Author: HaoBin
 * @Date 2018/1/24 21:46
 * 递归：取出某个文件夹下所有文件
 */
public class RecursionFloder {

    public static void displayFloders(File file) {
        // 获取当前文件夹下所有子文件/文件夹
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if( files[i].isFile()) {
                System.out.println(files[i].getName());
            } else {
                displayFloders(files[i]);
            }
        }
    }

    public static void main(String[] args) {
        displayFloders(new File("G:\\JAVA\\algorithm\\src\\main\\java"));
    }
}
