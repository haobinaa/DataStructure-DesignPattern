package com.haobin.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author HaoBin
 * @Create 2020/1/21 16:24
 * @Description: 排序数组中搜索元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 **/
public class SearchRange {

    /**
     * 题目要求 O(log n) 的时间复杂度， 考虑二分查找
     * 与普通的查找 target 不一样， 这次需要查找左右边界
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[] {-1, -1};
        if (nums.length == 0) {
            return ans;
        }
        ans[0] = findLeftPosition(nums, target);
        ans[1] = findRightPosition(nums, target);
        return ans;
    }

    /**
     * 查找左边界
     */
    private int findLeftPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                right = middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle;
            }
        }
        // target 大于所有元素
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 查找右边界
     */
    private int findRightPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = (left + right) / 2;
            // 当循环退出的时候, left 一定指向 target + 1的位置
            if (nums[middle] == target) {
                left = middle + 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle;
            }
        }
        // target 小于所有元素
        if (left == 0) {
            return -1;
        }
        return nums[left - 1] == target ? (left - 1) : -1;
    }
}
