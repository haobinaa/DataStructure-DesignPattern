package com.haobin.codeBlock.random;

import java.util.UUID;

/**
 * uuid 转 int
 * @Date 2022/1/4 11:27 上午
 * @author: leobhao
 */
public class IdIntegerGen {


    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(generateUniqueId());
        }
    }
}
