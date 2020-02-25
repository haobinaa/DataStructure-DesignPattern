package com.haobin.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HaoBin
 * @Create 2020/2/24 15:22
 * @Description: 最小覆盖子串
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 **/
public class MinWindow {


    /**
     * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引闭区间 [left, right] 称为一个「窗口」。
     * 2、我们先不断地增加 right 指针扩大窗口 [left, right]，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
     * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。
     * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
     */
    public static String minWindow(String s, String t) {
        String ans = "";
        if(s == null || s.length() == 0) {
            return ans;
        }
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int match = 0;
        int minLen = Integer.MAX_VALUE;
        for(char ch : t.toCharArray()){
            if (needs.containsKey(ch)) {
                needs.put(ch, needs.get(ch)+1);
            } else {
                needs.put(ch, 1);
            }
        }
        while(right < s.length()){
            char c1 = s.charAt(right);
            // 如果 need 包含 c1 则加入 window
            if(needs.containsKey(c1)){
                if (window.containsKey(c1)) {
                    window.put(c1, window.get(c1)+1);
                } else {
                    window.put(c1, 1);
                }
                if(window.get(c1) == needs.get(c1)){
                    match++;
                }
            }
            // window 已经包含了所有的 need
            while(match == needs.size()){
                if (minLen > (right-left)) {
                    minLen = right - left + 1;
                    ans = s.substring(left, right+1);
                }
                char c2 = s.charAt(left);
                if(needs.containsKey(c2)){
                    window.put(c2, window.get(c2)-1);
                    if(window.get(c2) < needs.get(c2)){
                        match--;
                    }
                }
                left++;
            }
            right++;
        }
        return ans;
    }

}
