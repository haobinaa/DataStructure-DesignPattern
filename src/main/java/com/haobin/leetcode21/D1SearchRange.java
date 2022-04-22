package com.haobin.leetcode21;

import java.lang.ref.WeakReference;

/**
 * 第一天
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * <p>
 * 排序数组使用二分法
 * <p>
 * <p>
 * 普通二分法搜索
 * int binarySearch(int[] nums, int target) {
 * int left = 0;
 * int right = nums.length - 1; // 注意
 * <p>
 * while(left <= right) {
 * int mid = left + (right - left) / 2;
 * if(nums[mid] == target)
 * return mid;
 * else if (nums[mid] < target)
 * left = mid + 1; // 注意
 * else if (nums[mid] > target)
 * right = mid - 1; // 注意
 * }
 * return -1;
 * }
 * 1. left + (right - left)/2 与 (left + right)/2 结果相同， 前面可以防止 left + right 过大而溢出
 * 2. 循环退出条件 while(left <= right), 这里是 <= ， 跟初始化赋值 right = nums.length -1 有关， <= 是左闭右闭， < 是左闭右开
 * 3. 终止条件 while(left<=right) 终止条件就是 left==right+1, 区间形式是 [right+1, right]
 * while(left < right) 终止条件就是 left == right， 区间形式是 [right, right]
 * 算法缺陷: 无法查找左右侧边界， 如[1,2,2,2,3]，target 为 2, 这里只能返回 index 为 2， 左侧边界1和右侧边界3无法处理
 * <p>
 * <p>
 * 左侧边界二分搜索
 * int left_bound(int[] nums, int target) {
 * if (nums.length == 0) return -1;
 * int left = 0;
 * int right = nums.length; // 注意
 * <p>
 * while (left < right) { // 注意
 * int mid = left + (right - left) / 2;
 * if (nums[mid] == target) {
 * right = mid;
 * } else if (nums[mid] < target) {
 * left = mid + 1;
 * } else if (nums[mid] > target) {
 * right = mid; // 注意
 * }
 * }
 * return left;
 * }
 * 1. while 中是 < 而不是 <=
 *
 * @Date 2022/3/8 10:08 AM
 * @author: leobhao
 */
public class D1SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};

        int l = 0, r = nums.length - 1; //二分范围
        while (l < r)                    //查找元素的开始位置
        {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        if (nums[r] != target) return new int[]{-1, -1}; //查找失败
        int L = r;
        l = 0;
        r = nums.length - 1;     //二分范围
        while (l < r)                    //查找元素的结束位置
        {
            int mid =l + (r - l) / 2 + 1;
            if (nums[mid] <= target)
                l = mid;
            else
                r = mid - 1;
        }
        return new int[]{L, r};


    }

}
