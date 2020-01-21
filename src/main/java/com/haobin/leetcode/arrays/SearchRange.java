package com.haobin.leetcode.arrays;

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
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
    }
}
