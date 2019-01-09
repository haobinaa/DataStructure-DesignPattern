/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm.str;

import java.util.Arrays;

/**
 * Leetcode: 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 *
 * 思路: 先利用Arrays.sort(strs)为数组排序，再将数组第一个元素和最后一个元素的字符从前往后对比即可(最长字符串和最短字符串的公共前缀)
 *
 *
 * @author HaoBin
 * @version $Id: CommonPrefix.java, v0.1 2019/1/9 17:33 HaoBin 
 */
public class CommonPrefix {

    public static String commonPrefix(String[] strs) {
        int len = strs.length;

        StringBuffer res = new StringBuffer();

        if (strs == null || strs.length == 0) {
            return "";
        }
        // 字符串数组按照升序排序
        Arrays.sort(strs);

        int m = strs[0].length();
        int n = strs[len-1].length();
        int num = Math.min(m, n);
        for (int i = 0; i < num; i++) {
            if (strs[0].charAt(i) == strs[len-1].charAt(i)) {
                res.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String[] strs = { "customer", "car", "cat" };
        System.out.println(CommonPrefix.commonPrefix(strs));
    }
}