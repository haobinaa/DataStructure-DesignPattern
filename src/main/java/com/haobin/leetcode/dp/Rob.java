package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/1/16 15:51
 * @Description: 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 **/
public class Rob {

    /**
     * 由于不可以在相邻的房屋闯入，所以在当前位置 n 房屋可盗窃的最大值
     * 要么就是 n-1 房屋可盗窃的最大值，要么就是 n-2 房屋可盗窃的最大值加上当前房屋的值，二者之间取最大值
     *
     * 动态规划方程 => dp = Max(dp[n-1], dp[n-2] + num[n])
     */
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i+1] = Math.max(dp[i], dp[i-1] + nums[i]);
        }
        return dp[nums.length];
    }
}
