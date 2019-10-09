/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm;


/**
 * #####################
 * 01背包问题：有编号分别为a,b,c,d,e的五件物品，它们的重量分别是2,2,6,5,4，它们的价值分别是6,3,5,4,6，每件物品数量只有一个
 * 现在给你个承重为C的背包，如何让背包里装入的物品具有最大的价值总和
 *
 * 动态规划求解:
 * 假设C=10.即为考虑abcde，C = 10时的最大价值，假设为f[5][10]
 * 原问题的解可以分解为两种情况：
 * （1）不考虑放入a只考虑放入bcde承重为C时的最大价值f[4][10]
 * （2）考虑放入a时的最大价值，即value[a]+f[4][10-weight[a]]
 *
 *  那么f[5][10]的最大值为:
 *  f[5][10] = MAX{f[4][10], (f[4][10-weight[a]] + value(a))}
 *
 *  表格第一行公式为:
 *  f[0][j] = 0,  j < wi (背包容量小于物品重量时价值为0)
 *          = vi, j > wi (背包容量大于物品重量时，价值为物品价值)
 *  表格第二行公式为:
 *  f[1][j] = MAX{f[0][j], (f[0][j-weight[a]] + value(a))}  （放入物品a或不放入物品a的两种情况）
 *  ........
 *
 *  整理出通用公式为:
 *  f[i][j] = 0,                                                 j < wi=0
 *          = w0,                                                j >=wi=0
 *          = f(i-1, j),                                         j < wi
 *          = MAX{f[i-1][j], (f[i-1][j-weight[a]] + value(a))},  j >=wi
 *
 *
 *##################################################
 * 完全背包问题
 */
public class Backpack {

    public static void main(String[] args) {
        int[] weights = {2,2,6,5,4};
        int[] price = {6,3,5,4,6};
        int capacity = 10;
        System.out.println(dpBackpack01(weights, price, capacity));
    }

    /**
     * 01 背包问题动态规划
     * @param weight 物品重量
     * @param price 物品价值
     * @param capacity 背包容量
     */
    public static int dpBackpack01(int[] weight, int[] price, int capacity) {
        // 行
        int row = weight.length - 1;
        int[][] solutionMatrix = new int[weight.length][capacity];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < capacity; j++) {
                if (i == 0) {
                    // 如果是第一行, 容量小于第一个物品价值是0，否则是第一个物品的重量
                    solutionMatrix[i][j] = (j < weight[i] ? 0 : price[i]);
                } else {
                    if (j < weight[i]) {
                        // 如果装不下物品i，则等于之前的最优解
                        solutionMatrix[i][j] = solutionMatrix[i-1][j];
                    } else {
                        // 如果装的下物品i，则在之前的最优解，和放入物品i后取最大值
                        solutionMatrix[i][j] = Math.max(solutionMatrix[i-1][j],
                                solutionMatrix[i-1][j-weight[i]] + price[i]);
                    }
                }
            }
        }
        // 最后一格的最优解
        return solutionMatrix[row][capacity-1];
    }
}