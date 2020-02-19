package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/2/19 15:06
 * @Description: 完全平方数
 *
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 **/
public class NumSquares {


    /**
     * dp[i] = MIN(dp[i], dp[i - j * j] + 1)， i表示当前数字，j*j表示平方数
     * 最坏的结果就是每次都为i, 即全部是1(i个1)
     */
    public int numSquares(int n) {
        // n=0 时结果为0
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j*j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }
}
