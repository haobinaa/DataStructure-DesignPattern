package com.haobin.leetcode.dfs;

/**
 * @Author HaoBin
 * @Create 2020/2/13 21:27
 * @Description: 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 *
 **/
public class SearchWord {

    // 上下左右
    private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int col;
    private int row;
    // 网格
    private char[][] board;
    // 标记走过的路径
    private boolean[][] marked;
    // 被搜索的单词
    private String word;


    public boolean exist(char[][] board, String word) {
        if (board.length == 0) {
            return false;
        }
        row = board.length;
        col = board[0].length;
        this.marked = new boolean[row][col];
        this.board = board;
        this.word = word;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, int start) {
        if (r < 0 || r >= row || c < 0 || c >= col || marked[r][c]) {
            return false;
        }
        if (start == word.length()-1) {
            return board[r][c] == word.charAt(start);
        }
        if (board[r][c] == word.charAt(start)) {
            marked[r][c] = true;
            for (int[] n : next) {
                if (dfs(r+n[0], c+n[1], start+1)) {
                    return true;
                }
            }
            marked[r][c] =false;
        }
        return false;
    }
}
