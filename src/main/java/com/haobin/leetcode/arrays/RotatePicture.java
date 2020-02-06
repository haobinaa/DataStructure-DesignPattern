package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/2/5 16:12
 * @Description: 旋转图像
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 **/
public class RotatePicture {

    /**
     * 思路： 反转整个数组，然后对角线交换
     * [                    [                  [
     *   [1,2,3],             [7,8,9],            [7,4,1],
     *   [4,5,6],    ---->    [4,5,6], ----->     [8,5,2],
     *   [7,8,9]              [1,2,3]             [9,6,3] 
     * ]                    ]                  ]
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 上下反转
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j] = temp;
            }
        }
        // 对角交换
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
