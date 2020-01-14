package com.haobin.leetcode.arrays;

import java.util.Arrays;

/**
 * @Author HaoBin
 * @Create 2020/1/13 19:13
 * @Description: 最接近三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。 返回这三个数的和。假定每组输入只存在唯一答案。 例如，给定数组 nums =
 * [-1，2，1，-4], 和 target = 1. 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 **/
public class ThreeSumClosest {


    /**
     * 与三数之和类似 1. 排序 2. l 指针指向 i+1, r 指针指向 nums.length -1 3. sum = nums[i] + nums[l] + nums[r] 4. sum > target 则 r--,
     * sum < target 则 l++, sum == nums[i] 则直接返回
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int aims = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                // 当前之和是否比上次计算更接近目标值
                if (Math.abs(sum - target) < Math.abs(aims - target)) {
                    aims = sum;
                }
                if (sum > target) {
                    r--;
                } else if (sum < target) {
                    l++;
                } else {
                    return aims;
                }
            }
        }
        return aims;
    }
}
