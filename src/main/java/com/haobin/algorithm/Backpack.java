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
 * f[1][j] = 0,  j < wi (背包容量小于物品重量时价值为0)
 * = vi, j > wi (背包容量大于物品重量时，价值为物品价值)
 * 表格第二行公式为:
 * f[2][j] = MAX{f[0][j], (f[0][j-weight[a]] + value(a))}  （放入物品a或不放入物品a的两种情况）
 * ........
 * <p>
 * 整理出通用公式为:
 * f[i][j] = 0,                                         j < wi && i=0
 * = w0,                                                j >=wi=0 && i=0
 * = f(i-1, j),                                         j < wi
 * = MAX{f[i-1][j], (f[i-1][j-weight[a]] + value(a))},  j >=wi
 *
 *
 * 参考连接：
 * - [javascript实现](https://segmentfault.com/a/1190000012829866/#articleHeader5)
 * - [java实现](https://blog.csdn.net/lanyu_01/article/details/79815801)
 */
public class Backpack {

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 1};
        int[] price = {2, 5, 3, 2};
        int capacity = 5;
        dpBackpack(weights, price, capacity);
        System.out.println(optimizeBackpack(weights, price, capacity));
    }

    /**
     * 01背包问题
     *
     *
     * 最优解选择了那些物品思路：
     * 从f[n-1][C] 逆着走向 f[0][0]
     * 设 i=n-1, j=W， 如果f[i][j] = f[i-1][j-w[i]] + v[i], 则说明已经放入了第i件物品
     * 因此我们只要当前行不等于上一行的总价值，就能挑出第i件物品，然后j减去该物品的重量，一直找到j = 0就行了
     * 注意：第一行(i=0)是否选择了物品的判断是容量(j)是否大于物品重量
     */
    public static int dpBackpack(int[] weight, int[] price, int capacity) {
        int row = weight.length;
        int col = capacity;
        // 这里补充第0行，防止下标越界
        int[][] solutionMatrix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 对应的物品下标为i-1
                if (j < weight[i - 1]) {
                    // 如果装不下物品i，则等于之前的最优解
                    solutionMatrix[i][j] = solutionMatrix[i - 1][j];
                } else {
                    // 如果装的下物品i，则在之前的最优解，和放入物品i后取最大值
                    solutionMatrix[i][j] = Math.max(solutionMatrix[i - 1][j],
                            solutionMatrix[i - 1][j - weight[i - 1]] + price[i - 1]);
                }

            }
        }
        // 逆推物品编号
        int j = capacity, w = 0;
        for (int i = row; i > 0; i--) {
            if (solutionMatrix[i][j] > solutionMatrix[i - 1][j]) {
                int num = i - 1;
                System.out.println("物品编号:" + num  + " 重量为:" + weight[i-1] + ", 价值为:" + price[i-1]);
                j = j - weight[i-1];
                w += weight[i-1];
            }
        }
        System.out.println("背包最大重量为:" + capacity + ", 现重量为:" + w + ", 总价值为:" + solutionMatrix[row][capacity]);
        return solutionMatrix[row][capacity - 1];
    }

    /**
     * 优化解法，压缩成一维数组
     *  max{f[i-1][v], f[i-1][v-w[i]] + v[i]} ->  max{f[v], f[v-w[i]] + w[i]}
     *
     * 将第i件物品放入容量为v的背包这个子问题转换成:
     * 前 i-1 件物品放入容量为 n 的背包， 价值为 f[i-1][n]
     * 如果放入第i件物品， 最大价值就是 f[i-1][n-w[i]] + w[i]
     *
     */
    public static int optimizeBackpack(int[] weight, int[] price, int capacity) {
        int[] dp = new int[capacity+1];
        for (int i = 1; i < weight.length+1; i++) {
            // 逆序
            for (int j = capacity; j >= weight[i-1]; j--) {
                dp[j] = Math.max(dp[j - weight[i-1]] + price[i-1], dp[j]);
            }
        }
        return dp[capacity];
    }
}