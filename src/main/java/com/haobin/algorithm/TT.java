/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author HaoBin
 * @version $Id: TT.java, v0.1 2019/2/28 12:42 HaoBin 
 */
public class TT {
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 1)
            return strs[0];
        String firstStr = strs[0];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < firstStr.length(); i++) {
            char current = firstStr.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() - 1 < i || strs[j].charAt(i) != current)
                    return sb.toString();
                if (strs[j].charAt(i) == current && j == strs.length - 1) {
                    sb.append(current);
                    System.out.println(j);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"c","acc","ccc"};
        System.out.println(longestCommonPrefix(strs));
    }
}