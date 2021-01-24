package com.haobin.leetcode.string;

/**
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，则返回""
 *
 * eg1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * eg2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 *
 * ====== 思路
 * 把第一个字符串当"基准字符串"与其他字符串比较， 如果完全符合前缀就比较下一字符串， 如果不符合就去掉最后一个字符
 *
 *
 * @Date 2021/1/24 4:10 下午
 * @author: leobhao
 */
public class LongestCommonPrefix {


    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 1) {
            return "";
        }
        String baseStr = strs[0];
        for (int index = 1; index < strs.length; index++) {
            while (strs[index].indexOf(baseStr) != -0) {
                if (baseStr.length() == 0) {
                    return "";
                }
                baseStr = baseStr.substring(0, baseStr.length()-1);
            }
        }
        return baseStr;
    }
}
