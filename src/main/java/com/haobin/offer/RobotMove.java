package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/4 21:42
 * @description: 机器人的运动范围
 * 题目描述:
 * 地上有一个 m 行和 n 列的方格。一个机器人从坐标 (0, 0) 的格子开始移动，每一次只能向左右上下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的"数位之和"大于 k 的格子。
 * 例如，当 k 为 18 时，机器人能够进入方格 (35,37)，因为 3+5+3+7=18。
 * 但是，它不能进入方格 (35,38)，因为 3+5+3+8=19。请问该机器人能够达到多少个格子
 * <p>
 * <p>
 * 解题思路:
 * 深度优先搜索来解决，回溯是深度优先的一种特例
 **/
public class RobotMove {

    // 上下左右
    private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    // 记录能达到的格子数
    private int cnt = 0;
    // 行
    private int rows;
    // 列
    private int cols;
    // 阈值K
    private int threshold;
    // 格子迷宫所代表的值
    private int[][] digitSum;


    public int movingCount(int threshold, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        initDigitSum();
        boolean[][] marked = new boolean[rows][cols];
        dfs(marked, 0, 0);
        return cnt;
    }

    // 深度优先遍历
    private void dfs(boolean[][] marked, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || marked[r][c])
            return;
        marked[r][c] = true;
        if (this.digitSum[r][c] > this.threshold)
            return;
        cnt++;
        // 上下左右探索，直至不能移动为止
        for (int[] n : next)
            dfs(marked, r + n[0], c + n[1]);
    }

    // 将每个方格代表的值初始化好
    private void initDigitSum() {
        // 使用一个正方形来记录坐标的值
        int[] digitSumOne = new int[Math.max(rows, cols)];
        for (int i = 0; i < digitSumOne.length; i++) {
            int n = i;
            while (n > 0) {
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        // 初始化一个含有值的迷宫
        this.digitSum = new int[rows][cols];
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                this.digitSum[i][j] = digitSumOne[i] + digitSumOne[j];
    }
}
