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
     * 将字符串反转， 与之比较找出最长公共子穿
     * 需要考虑的情况是，源字符串存在反向副本的情况：S=abacdfgdcaba 反转后 S1=abacdgfdcaba
     * 最长子穿是一个对称副本: abacd, 但并不是回文的
     *
     * 针对这种情况：
     * 每当我们找到最长的公共子串的候选项时，都需要检查子串的索引是否与反向子串的原始索引相同。
     * 如果相同，那么我们尝试更新目前为止找到的最长回文子串；如果不是，我们就跳过这个候选项并继续寻找下一个候选。
     *
     * 动态规划求最长公共子串
     *
     */
    public String longestPalindrome(String s) {
        String reverse = new StringBuffer(s).reverse().toString();

        return null;
    }
}
