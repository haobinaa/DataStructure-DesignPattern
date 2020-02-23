package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/2/23 22:21
 * @Description: 和为K的数组
 *
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 **/
public class SubArraySumK {


    /**
     * sum[i] 用于存储 nums 的序号 0 到 (i-1) 之间元素的和，
     * 所以，为了确定子数组 nums [i:j] 的元素总和，我们可以直接使用 sum [j + 1] - sum[i]，
     *
     */
    public int subarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length+1];
        int count = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        for (int start = 0; start < nums.length; start++) {
            for (int end = start+1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
