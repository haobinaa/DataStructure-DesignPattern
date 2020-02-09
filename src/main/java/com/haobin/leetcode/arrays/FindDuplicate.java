package com.haobin.leetcode.arrays;

import java.util.Arrays;

/**
 * @Author HaoBin
 * @Create 2020/2/6 22:56
 * @Description: 寻找重复数
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 *
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3

 **/
public class FindDuplicate {


    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i+1]) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 抽屉原理， 二分查找
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            int count = 0;
            for (int i = 0; i < len; i++) {
                if (nums[i] <= middle) {
                    count++;
                }
            }
            if (count > middle) {
                // 多于 middle 个数， 重复区间就是 [left, middle]
                right = middle;
            } else {
                // 重复的数位于 [middle+1, right]
                left = middle + 1;
            }
        }
        return left;
    }
}
