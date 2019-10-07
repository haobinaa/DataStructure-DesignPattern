package com.haobin.offer;

/**
 * @Author haobin
 * @Date 2019-10-07 18:16
 * @Description 整数拆分(剪绳子)
 * 题目描述:
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积
 *
 * eg:
 * n = 2
 * return 1 (2 = 1 + 1)
 *
 * n = 10
 * return 36 (10 = 3 + 3 + 4)
 *
 *
 *
 *
 * 解题思路：
 * 动态规划：
 **/
public class IntegerBreaker {

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            for (int j = 1; j < i; j++)
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));
        return dp[n];
    }
}
