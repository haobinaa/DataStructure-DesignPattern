package com.haobin.leetcode.dp;

import java.util.Map;

/**
 * @Author HaoBin
 * @Create 2020/2/25 16:30
 * @Description: 最大正方形
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 **/
public class MaxSquare {


    /**
     * 定义状态:
     * dp[i][j] 代表是由 1 组成的最大正方形的边长, 并且 matrix[i][j] 是正方形的右下角
     * 受限于分别以 左边，左上，右边元素为右下角的正方形边长
     *
     *
     * 状态转移:
     *  dp[i][j] = min(dp[i-1][j], dp[i,j-1], dp[i-1,j-1])+1
     *  (上，左， 左上)+1 的含义：
     *  若某格子值为 1 ，则以此为右下角的正方形的、最大边长为：上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格
     *  (1) 若形成正方形（非单 1），以当前为右下角的视角看，则需要：当前格、上、左、左上都是 1
     *  (2) 可以换个角度：当前格、上、左、左上都不能受 0 的限制，才能成为正方形(任何一个限制有0都限制了正方形的边长)
     *
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        // 第一行第一列都是0
        int[][] dp = new int[height+1][width+1];
        int maxSide = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row+1][col+1]);
                }
            }
        }
        return maxSide*maxSide;
    }
}
