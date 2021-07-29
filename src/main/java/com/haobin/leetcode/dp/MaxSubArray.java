package com.haobin.leetcode.dp;

/**
 * 子数组最大累加和
 * @Date 2021/7/28 9:02 下午
 * @author: leobhao
 */
public class MaxSubArray {


    public int maxSubArray(int[] nums) {
        int sum = 0;
        int ans = nums[0];
        for (int num : nums) {
            sum = Math.max(sum+num, num);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
