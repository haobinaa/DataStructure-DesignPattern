package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/2/17 16:54
 * @Description: 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 **/
public class MiniPathSum {

    /**
     * 状态定义:
     * 设 dp 为大小 m×n 矩阵，其中 dp[i][j] 的值代表直到走到 (i,j) 的最小路径和
     *
     * 转移方程:
     * 1. 只能从左上往右下走，则当前(i, j) 只能从左方 (i-1, j) 或 上方 (i, j-1) 走到，所以只需要考虑左边界和上边界
     * 2. 走到当前单元格(i,j)的最小路径和等于 Min(dp[i-1][j], dp[i][j-1])+grid[i][j], 从左方或上方的最小路径中小的哪个加当前单元格的值,
     *    包含四种情况:
     *    (1). 左方和上方都不是边界(i!=0 && j!=0)， dp[i][j] = min(dp[i-1][j], dp[i][j-1])+grid[i][j]
     *    (2). 只有左方是边界,只能从上方来(i=0 && j!=0), dp[i][j] = dp[i,j-1]+grid[i][j]
     *    (3). 只有上方是边界(i!=0 && j=0), dp[i][j] = dp[i-1][j]+grid[i][j]
     *    (4). 左上都是边界(i=0,j=0)即在起点位置, dp[i][j] = grid[i][j]
     */
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i-1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
