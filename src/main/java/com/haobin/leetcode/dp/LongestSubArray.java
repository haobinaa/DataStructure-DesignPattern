package com.haobin.leetcode.dp;

import java.util.Arrays;

/**
 * @Author HaoBin
 * @Create 2020/2/24 10:56
 * @Description: 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 **/
public class LongestSubArray {

    /**
     * 动态规划解法
     * 定义状态： dp[i] 表示以 num[i] 结尾的最长上升子序列长度
     * 状态转移:
     *  遍历到 nums[i] 时，考虑把索引 i 之前的所有的数都看一遍，只要当前的数 nums[i] 严格大于之前的某个数，
     *  那么 nums[i] 就可以接在这个数后面形成一个更长的上升子序列。
     *  因此，dp[i] 就等于索引 i 之前严格小于 nums[i] 的状态最大者 +1。
     *
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
