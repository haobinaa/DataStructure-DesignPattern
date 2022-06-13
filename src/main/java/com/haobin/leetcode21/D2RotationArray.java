package com.haobin.leetcode21;

import java.util.Arrays;

/**
 * 旋转数组
 *
 * 给你一个数组，将数组中的元素向右轮转 k个位置，其中 k 是非负数。
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 *
 * @Date 2022/6/10 8:52 AM
 * @author: leobhao
 */
public class D2RotationArray {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rotate1(nums, 3);
        System.out.println(Arrays.toString(nums));
    }


    public static void rotate(int[] nums, int k) {
        int[]ans = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            ans[(i+k) % nums.length] = nums[i];
        }
        System.arraycopy(ans, 0, nums, 0, nums.length);
    }


    /**
     * 1. reverse 数组
     * 2. reverse 0, k-1
     * 3. reverse k+1, n-1
     *
     */
    public static void rotate1(int[] nums, int k) {
        // 防止 k > num.length
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start , int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
