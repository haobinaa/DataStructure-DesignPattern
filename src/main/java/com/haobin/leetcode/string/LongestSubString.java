package com.haobin.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author HaoBin
 * @Create 2020/1/14 16:51
 * @Description: 最长无重复子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 **/
public class LongestSubString {

    /**
     * 暴力解法:
     * 枚举所有可能字符串(双重循环)，记录最大的连续位置(判断是否在当前[start, end]区间内)22
     *
     * 滑动窗口解法：
     * 1. 使用一个map记录存储的字符， key 为字符， value 为字符位置+1(从字符位置后一个开始不重复)
     * 2. [start, end] 指针记录不重复子串的位置
     * 3. end 向后移， 如果遇到 [start, end] 区间内相同的字符串， 将字符作为 key 获取 value 来更新 start
     * 4. map 和 ans 都会不断更新
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char character = s.charAt(end);
            if (window.containsKey(character)) {
                start = Math.max(start, window.get(character));
            }
            ans = Math.max(ans, end - start + 1);
            window.put(character, end+1);
        }
        return ans;
    }
}
