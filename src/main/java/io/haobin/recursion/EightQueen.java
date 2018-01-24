package io.haobin.recursion;

/**
 * 八皇后
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

    public static int check(int row, int col) {

    }
}
