package com.haobin.leetcode.string;

/**
 * @Author HaoBin
 * @Create 2020/1/19 12:01
 * @Description: 最长回文子穿
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 **/
public class LongestSubPalindrome {

    /**
     * 解法1： 暴力解法(该解法会超出时间限制)
     * 穷举所有子串，找出最长回文子串
     */
    public String longestPalindrome1(String s) {
        String ans = "";
        int max = 0;
        for (int i = 0; i <s.length() ; i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String subStr = s.substring(i, j);
                if (isPalindrome(subStr) && subStr.length() > max) {
                    ans = subStr;
                    max = Math.max(max, ans.length());
                }
            }
        }
        return ans;
    }

    /**
     * 判断是否是回文
     */
    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length()/2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i -1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 动态规划：
     * 1. 定义状态：dp[i][j] 表示子串 s[i, j] 是否为回文子串
     * 2. 状态转移：回文串的子穿必定是回文串，并且头尾字符相等，那么状态转移方程是
     * => d[i][j] = (s[i] == s[j]) && d[i+1][j-1]  头尾字符相等，并且子串也是回文
     * 这里需要注意两点:
     * (1) i <=j , 所以在动态规划的二维表格中， 只需要填写上半部分即可
     * (2) dp[i+1][j-1] 的边界条件： [i+1, j-1]不构成一个区间，即子串长度小于2 (j-1) - (i+1) + 1 < 2 ===> j-i<3
     * 综上， 当 s[i] == s[j] 并且 j-i<3 的时候，d[i][j] 是回文
     * 3. 初始化
     * 4. 输出， 当 d[i][j] 为回文，就可以得出 s[i,j] 的长度
     *
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        // 初始化动态规划表格
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        int start = 0;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start+maxLen);
    }

}
