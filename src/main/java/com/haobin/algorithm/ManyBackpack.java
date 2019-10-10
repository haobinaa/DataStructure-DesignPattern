package com.haobin.algorithm;

/**
 * @author: HaoBin
 * @create: 2019/10/10 11:38
 * @description: 多重背包
 *
 * 题目描述:
 * 有编号分别为a,b,c的三件物品，它们的重量分别是1，2，2，它们的价值分别是6，10，20，他们的数目分别是10，5，2，
 * 现在给你个承重为 8 的背包，如何让背包里装入的物品具有最大的价值总和
 *
 * 多重背包与01背包不同的是多重背包每个物品的数量是给定的，可能不是一个，也不是无限个
 *
 *  递推公式是 f[i][y] = max{ f[i-1][y], f[i-1][y-weight[i]] + value[i]}
 *  但这里放入物品i时， 有两种情况
 *  (1) 第i件物品一件也不放： f[i][y] = f[i-1][y]
 *  (2) 第i件物品放入k个: f[i][y] = max{ f[i-1][y], f[i-1][y-k*weight[i]]+k*value[i]}  其中 1<= k <= y/weight[i], 并且k在物品数量限制以内
 **/
public class ManyBackpack {


    public static void main(String[] args) {
        int[] weight = {1,2,2};
        int[] price = {6,10,20};
        int[] nums = {10,5,2};
        int capacity = 8;
        System.out.println(dpManyBackpack(weight, price, capacity, nums));
    }
    /**
     *
     * @param weight 物品重量
     * @param price 物品价格
     * @param capacity 背包容量
     * @param num 物品数量
     * @return
     */
    public static int dpManyBackpack(int[] weight, int[] price, int capacity, int[] num) {
        int row = weight.length;
        int col = capacity;
        int[][] dp = new int[row+1][col+1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (weight[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 考虑物品的数量限制
                    int maxN = Math.min(num[i-1], j/weight[i-1]);
                    for (int k = 0; k <= maxN; k++) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - k*weight[i-1]] + k*price[i-1]);
                    }
                }
            }
        }
        return dp[row][col];
    }
}
