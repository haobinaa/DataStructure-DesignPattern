package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/2/5 16:38
 * @Description: 除自身以外的乘积
 *
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 **/
public class ProductExceptSelf {


    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            // 左边数乘积
            ans[i] = k;
            k *= nums[i];
        }
        k = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 右边数乘积
            ans[i] *= k;
            k *= nums[i];
        }
        return ans;
    }
}
