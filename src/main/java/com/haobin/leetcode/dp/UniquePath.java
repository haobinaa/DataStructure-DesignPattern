package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/2/19 10:02
 * @Description: 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 **/
public class UniquePath {


    /**
     * 定义状态:
     * dp[i][j] 为 (i,j) 的路径和
     *
     * 状态转移:
     * dp[i][j] = dp[i-1][j]+dp[i][j-1], 考虑边界情况
     * (1) i!=0,j=0, dp[i][j] = 1
     * (2) j!=0,i=0, dp[i][j] = 1
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
