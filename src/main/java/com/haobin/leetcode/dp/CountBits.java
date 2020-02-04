package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/2/1 18:04
 * @Description: 比特计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 *
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 **/
public class CountBits {


    /**
     * 动态规划解法
     * 根据汉明重量 n & (n - 1) 可以消除最低位的 1
     * 可以得到状态转移方程
     * dp[i] = dp[i & (i - 1)] + 1
     */
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i < ans.length; i++) {
            ans[i] = ans[i & (i -1)] + 1;
        }
        return ans;
    }
}
