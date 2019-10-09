/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm;


import java.util.ArrayList;
import java.util.List;

/**
 * #####################
 * 01背包问题：有编号分别为a,b,c,d,e的五件物品，它们的重量分别是2,2,6,5,4，它们的价值分别是6,3,5,4,6，每件物品数量只有一个
 * 现在给你个承重为C的背包，如何让背包里装入的物品具有最大的价值总和
 * <p>
 * 动态规划求解:
 * 假设C=10.即为考虑abcde，C = 10时的最大价值，假设为f[5][10]
 * 原问题的解可以分解为两种情况：
 * （1）不考虑放入a只考虑放入bcde承重为C时的最大价值f[4][10]
 * （2）考虑放入a时的最大价值，即value[a]+f[4][10-weight[a]]
 * <p>
 * 那么f[5][10]的最大值为:
 * f[5][10] = MAX{f[4][10], (f[4][10-weight[a]] + value(a))}
 * <p>
 * 表格第一行公式为:
 * f[0][j] = 0,  j < wi (背包容量小于物品重量时价值为0)
 * = vi, j > wi (背包容量大于物品重量时，价值为物品价值)
 * 表格第二行公式为:
 * f[1][j] = MAX{f[0][j], (f[0][j-weight[a]] + value(a))}  （放入物品a或不放入物品a的两种情况）
 * ........
 * <p>
 * 整理出通用公式为:
 * f[i][j] = 0,                                                 j < wi=0
 * = w0,                                                j >=wi=0
 * = f(i-1, j),                                         j < wi
 * = MAX{f[i-1][j], (f[i-1][j-weight[a]] + value(a))},  j >=wi
 */
public class Backpack {

    public static void main(String[] args) {
        int[] weights = {2,3,4,1};
        int[] price = {2,5,3,2};
        int capacity = 5;
        System.out.println(dpBackpack(weights, price, capacity));
        dpBackpackAndChoosed(weights, price, capacity);
    }

    /**
     * 01 背包问题动态规划
     *
     * @param weight   物品重量
     * @param price    物品价值
     * @param capacity 背包容量
     */
    public static int dpBackpack(int[] weight, int[] price, int capacity) {
        // 行
        int row = weight.length - 1;
        int[][] solutionMatrix = new int[weight.length][capacity+1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0) {
                    // 如果是第一行, 容量小于第一个物品价值是0，否则是第一个物品的重量
                    solutionMatrix[i][j] = (j < weight[i] ? 0 : price[i]);
                } else {
                    if (j < weight[i]) {
                        // 如果装不下物品i，则等于之前的最优解
                        solutionMatrix[i][j] = solutionMatrix[i - 1][j];
                    } else {
                        // 如果装的下物品i，则在之前的最优解，和放入物品i后取最大值
                        solutionMatrix[i][j] = Math.max(solutionMatrix[i - 1][j],
                                solutionMatrix[i - 1][j - weight[i]] + price[i]);
                    }
                }
            }
        }
        // 最后一格的最优解
        return solutionMatrix[row][capacity];
    }

    /**
     * 最优解选择了那些物品
     * 从f[n-1][C] 逆着走向 f[0][0]
     * 设 i=n-1, j=W， 如果f[i][j] = f[i-1][j-w[i]] + v[i], 则说明已经放入了第i件物品
     * 因此我们只要当前行不等于上一行的总价值，就能挑出第i件物品，然后j减去该物品的重量，一直找到j = 0就行了
     * 注意：第一行(i=0)是否选择了物品的判断是容量(j)是否大于物品重量
     */
    public static int dpBackpackAndChoosed(int[] weight, int[] price, int capacity) {
        // 行
        int row = weight.length - 1;
        int[][] solutionMatrix = new int[weight.length][capacity+1];
        List<Integer> selected = new ArrayList<>();
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0) {
                    // 如果是第一行, 容量小于第一个物品价值是0，否则是第一个物品的重量
                    solutionMatrix[i][j] = (j < weight[i] ? 0 : price[i]);
                } else {
                    if (j < weight[i]) {
                        // 如果装不下物品i，则等于之前的最优解
                        solutionMatrix[i][j] = solutionMatrix[i - 1][j];
                    } else {
                        // 如果装的下物品i，则在之前的最优解，和放入物品i后取最大值
                        solutionMatrix[i][j] = Math.max(solutionMatrix[i - 1][j],
                                solutionMatrix[i - 1][j - weight[i]] + price[i]);
                    }
                }
            }
        }
        int j = capacity, w = 0;
        for (int i = row; i >= 0; i--) {
            if (i == 0) {
                if (j >= weight[i]) {
                    selected.add(i);
                    System.out.println("物品:" + i + ", 重量为:" + weight[i] + ", 价值为:" + price[i]);
                    j = j - weight[i];
                    w += weight[i];
                }
            } else if (solutionMatrix[i][j] > solutionMatrix[i - 1][j]) {
                selected.add(i);
                System.out.println("物品:" + i + " 重量为:" + weight[i] + ", 价值为:" + price[i]);
                j = j - weight[i];
                w += weight[i];
            }
        }
        System.out.println("背包最大重量为:" + capacity + ", 现重量为:" + w + ", 总价值为:" + solutionMatrix[row][capacity]);
        return solutionMatrix[row][capacity - 1];
    }
}