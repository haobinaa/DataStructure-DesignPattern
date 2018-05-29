package com.haobin.datastructure.recursion;

/**
 * 八皇后
 *
 * @Author: HaoBin
 * @Date 2018/1/24 22:04
 */
public class EightQueen {

    /**
     * 0表示没有皇后 1表示有皇后
     */
    static int[][] map = new int[8][8];

    static int count = 1;

    public static void main(String[] args) {
    }

    public static void show() {
        System.out.println("第" + count++ + "种摆法");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 判断是否能放置
     * @param row
     * @param col
     * @return
     */
    public static boolean check(int row, int col) {
        // 上面
        for (int i = row - 1; i >= 0; i--) {
            if (map[i][col] == 1)
                return false;
        }
        // 左斜上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (map[i][j] == 1)
                return false;
        }
        // 右斜上
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            if (map[i][j] == 1)
                return false;
        }
        return true;
    }

    public static void play(int row) {
        // 遍历当前行的所有单元格
        for (int i = 0; i < 8; i++) {
            if (check(row, i)) {
                map[row][i] = 1;
                if (row == 7) {
                    show();
                } else {
                    // 向下一行放置
                    play(row + 1);
                }
            }
        }
    }
}
