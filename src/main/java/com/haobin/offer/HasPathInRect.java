package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/25 17:07
 * @description: 矩阵中的路径
 * <p>
 * 题目描述
 * 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向上下左右移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * <p>
 * <p>
 * 解题思路:
 * 使用回溯法（backtracking）进行求解，它是一种暴力搜索方法，通过搜索所有可能的结果来求解问题。
 * 回溯法在一次搜索结束时需要进行回溯（回退），将这一次搜索过程中设置的状态进行清除，从而开始一次新的搜索过程。
 **/
public class HasPathInRect {
    // 左，右，上，下
    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    // 行
    private int rows;
    // 列
    private int col;


    public boolean hasPath(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) {
            return false;
        }
        this.rows = rows;
        this.col = cols;
        // 标记那些位置已经被访问过了
        boolean[][] marked = new boolean[rows][cols];
        // 将传入数组转成矩阵(二维数组)
        char[][] matrix = buildMatrix(array);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtracking(matrix, str, marked, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean backtracking(char[][] matrix, char[] str, boolean[][] marked, int pathLen, int r, int c) {
        // 匹配到字符串
        if (pathLen == str.length) {
            return true;
        }
        // 是否触碰矩阵边界：
        // 1. 行是否触碰边界
        // 2. 列是否触碰边界
        // 3. 是否是匹配字符
        // 4. 是否已经访问过
        if (r < 0 || r >= rows || c < 0 || c >= col || matrix[r][c] != str[pathLen] || marked[r][c]) {
            return false;
        }
        // 如果匹配到，则标记已经被访问
        marked[r][c] = true;
        // 回溯访问上下左右
        for (int[] n : next) {
            if (backtracking(matrix, str, marked, pathLen + 1, r + n[0], c + n[1])) {
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }


    /**
     * 构建矩阵
     */
    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][col];
        for (int i = 0, idx = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = array[idx++];
            }
        }
        return matrix;
    }


    /**
     * 用例测试
     */
    public static void main(String[] args) {
        HasPathInRect h = new HasPathInRect();
        char[] array = {
                'a', 'b', 'c',
                'e', 's', 'f',
                'c', 's', 'a',
                'd', 'e', 'e'
        };
        char[] char1 = {'b', 's', 'f', 'a'};
        char[] char2 = {'a', 'f', 'a'};
        System.out.println(h.hasPath(array, 4, 3, char1));
        System.out.println(h.hasPath(array, 4, 3, char2));
    }
}
