package com.haobin.offer;

/**
 * @Author haobin
 * @Date 2019-10-07 18:16
 * @Description 整数拆分(剪绳子)
 * 题目描述:
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积
 * <p>
 * eg:
 * n = 2
 * return 1 (2 = 1 + 1)
 * <p>
 * n = 10
 * return 36 (10 = 3 + 3 + 4)
 * <p>
 * 动态规划思路:
 * f(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值
 * f(n)=max{f(j) * f(n-j)} ,  0 < i < n
 * 长度为1的时候，不能剪 => f(1) = 1
 * 长度为2时，只可能剪成两端1 => f(2) = 1
 * 长度为3时，可以剪成长度为1和2或三段1，但1*2>1*1*1 => f(3) = 2
 **/
public class IntegerBreaker {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i/2; j++) {
                // 如果剪j， 那么比较直接相乘和两段的最优解乘积
                dp[i] = Math.max(dp[j] * dp[i-j], j * (i-j));
            }
        }
        return dp[n];
    }
}
