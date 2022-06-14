package com.haobin.leetcode21;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 *
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *
 * @Date 2022/6/14 5:02 PM
 * @author: leobhao
 */
public class D6LengthOfLongestSubstring {


    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 1) {
            return 1;
        }
        Map<Character, Integer> window = new HashMap<>();
        int start =0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (window.containsKey(s.charAt(i))) {
                start = Math.max(window.get(s.charAt(i))+1, start);
            }
            max = Math.max(max, i - start + 1);
            window.put(s.charAt(i), i);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
