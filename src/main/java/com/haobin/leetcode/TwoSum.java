package com.haobin.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author HaoBin
 * @Create 2020/1/9 18:01
 * @Description: 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 **/
public class TwoSum {

    /**
     * 解法1 比较粗糙，直接遍历
     */
    public  int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i ++) {
            for (int j = i+1; j < nums.length; j++) {
                if ((nums[i] + nums[j] == target)) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 解法2 使用 Hash 表存储
     * @param nums
     * @param target
     */
    public  int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (result.containsKey(nums[i])) {
                return new int[] {result.get(nums[i]), i};
            }
            result.put(target - nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(nums, target)));
    }
}
