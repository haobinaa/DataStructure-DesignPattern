package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/2/18 16:55
 * @Description: 统计回文子串
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 *
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 **/
public class CountPalindromeSubString {


    /**
     * dp[i][j] 表示 String[i,j] 是否是回文子串. 头尾字符相等并且子串也是回文
     *
     * 状态转移方程：
     * dp[i][j] = dp[i+1][j-1] && str[i]==str[j]
     */
    public int countSubstrings(String s) {
        int ans = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                // 考虑单字符的情况
                System.out.println(i + "," + j);
                if (s.charAt(i) == s.charAt(j) && ((j-i) < 2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }



}
