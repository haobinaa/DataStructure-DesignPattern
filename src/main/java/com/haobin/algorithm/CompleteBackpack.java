package com.haobin.algorithm;

/**
 * @author: HaoBin
 * @create: 2019/10/9 18:05
 * @description: 完全背包问题
 *
 * ###################################
 * 完全背包问题与01背包的区别是，完全背包每个物品可以放多次
 * 01背包只考虑放与不放进去两种情况
 * 完全背包要考虑 放0、放1、放2...的情况，
 *
 * 选择放与不放:
 * f[i][j] = max{ f[i-1][j], f[i-1][j-weight[i]] + value[i]}
 *
 * 选择放k次i种物品:
 * f[i][j] = max{ f[i-1][j], f[i-1][j-k*weight[i]] + k*value[i]}
 *
 * 这里k不是无限的， 它受背包容量与单件物品重量限制，即 j/weight[i]
 * 假设只有1种商品，它的重量为20，背包的容量为60，那么它就应该放3个，在遍历时，就0、1、2、3地依次尝试。
 **/
public class CompleteBackpack {

    public static void main(String[] args) {
        int[] weight = {3, 2, 2};
        int[] price = {5, 10, 20};
        int capacity = 5;
        System.out.println(dbCompleteBackpack(weight, price, capacity));
    }

    public static int dbCompleteBackpack(int[] weight, int[] price, int capacity) {
        int[][] dp = new int[weight.length][capacity+1];
        for (int i = 0; i <= capacity; i++) {
            dp[-1][i] = 0;
        }
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                int bound = j/weight[i];
                for (int k = 0; k <= bound; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - k*weight[i]] + k * price[i]);
                }
            }
        }
        return dp[weight.length-1][capacity];
    }
}
