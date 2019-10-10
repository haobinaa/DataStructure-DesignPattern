package com.haobin.algorithm;

/**
 * @author: HaoBin
 * @create: 2019/10/9 18:05
 * @description: 完全背包问题
 *
 * ###################################
 *
 * 完全背包问题与01背包问题的区别就是物品数量是无限的
 *
 * 在放入 i 个物品时， 应该还要考虑还可能继续放入i， 那么递推公式应该变成:
 * max{f[i-1][j], f[i][y-weight[i]]+value[i]}（这里不在是i-1行了）

 *
 *
 **/
public class CompleteBackpack {

    public static void main(String[] args) {
        int[] weight = {2,3,4,7};
        int[] price = {1,3,5,9};
        int capacity = 10;
        System.out.println(dbCompleteBackpack(weight, price, capacity));
    }

    public static int dbCompleteBackpack(int[] weight, int[] price, int capacity) {
        int row = weight.length;
        int col = capacity;
        // 第0行初始化为0
        int[][] dp = new int[row+1][col+1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // 初始化第一行为0， price和weight的下标都对应减一
                if (weight[i-1] > j) {
                    // 物品重量大于容量，则不装入背包
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-weight[i-1]] + price[i-1]);
                }
            }
        }
        return dp[row][col];
    }
}
