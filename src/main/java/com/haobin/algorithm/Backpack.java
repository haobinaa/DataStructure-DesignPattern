/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm;

/**
 * 问题描述： 一个背包的总容量为V,现在有N类物品,第i类物品的重量为weight[i],价值为value[i] 那么往该背包里装东西,怎样装才能使得最终包内物品的总价值最大。
 *
 * 解法: 1) 求背包所含物品的最大值: 利用动态规划求最优值的方法。 假设用dp[N][V]来存储中间状态值,dp[i][j]表示前i件物品能装入容量为j的背包中的物品价值总和的最大值(注意是最大值),则我们最终只需求知dp[i=N][j=V]的值，即为题目所求。
 * 现在考虑动态规划数组dp[i][j]的状态转移方程： 假设我们已经求出前i-1件物品装入容量j的背包的价值总和最大值为dp[i-1][j],固定容量j的值不变，则对第i件物品的装法讨论如下：
 * 1、若weight[i]>j,则第i件物品肯定不能装入容量为j的背包，此时dp[i][j]=dp[i-1][j] 2、若weight[i]<=j,则首先明确的是这件物品是可以装入容量为j的背包的，那么如果我们将该物品装入，则有
 * dp[i][j]=dp[i-1][j-weight[i]]+value[i]
 *
 * 随之而来的问题是我们要判断第i件物品装到容量为j的背包后,背包内的总价值是否是最大？其实很好判断，即如果装了第i件物品后的总价值dp[i-1][j-weight[i]]+value[i]>没装之前的总价值最大值dp[i-1][j]，则肯是最大的；
 * 反之则说明第i件物品不必装入容量为j的背包(装了之后总价值反而变小，那么肯定就不需要装嘛)
 *
 * 故，状态转移方程如下：dp[i][j] = (dp[i-1][j] > (dp[i-1][j-weight[i]]+value[i]))? dp[i-1][j]:(dp[i-1][j-weight[i]]+value[i])
 *
 *
 * 2) 求出装入背包物品的编号: 采用逆推的思路来处理，如果对于dp[i][j]>dp[i-1][j]，则说明第i个物品肯定被放入了背包，此时我们再考察dp[i-1][j-weight[i]]的编号就可以了。
 *
 * 参考连接:https://blog.csdn.net/lanyu_01/article/details/79815801
 *
 * @author HaoBin
 * @version $Id: Backpack.java, v0.1 2019/1/9 17:58 HaoBin
 */
public class Backpack {

    public static String dpBackpack(int V, int N, int[] weight, int[] value) {
        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        //逆推找出装入背包的所有商品的编号
        int j = V;
        String numStr = "";
        for (int i = N; i > 0; i--) {
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if (dp[i][j] > dp[i - 1][j]) {
                numStr = i + " " + numStr;
                j = j - weight[i - 1];
            }
            if (j == 0) {
                break;
            }
        }
        return numStr;
    }
}