package com.haobin.leetcode.dfs;

/**
 * @Author HaoBin
 * @Create 2020/2/25 16:22
 * @Description: 目标和
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 *
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 **/
public class FindTargetSum {


    public int findTargetSumWays(int[] nums, int S) {
        return findTarget(nums, 0, S);
    }

    /**
     * dfs 回溯
     * 每次对当前数字取正数或负数
     * 如果到了数组结尾target为0就代表是一种解法
     */
    private int findTarget(int[] nums, int start ,int s) {
        if (start == nums.length) {
            return s == 0 ? 1 : 0;
        }
        return findTarget(nums, start + 1, s+nums[start]) + findTarget(nums, start+1, s-nums[start]);
    }
}
