package com.haobin.leetcode.arrays;

/**
 * @Author haobin
 * @Date 2020/1/31 10:45 下午
 * @Description 下一个排序
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 字典序可以理解成元素的全排列
 * 下一个更大的排列可以理解位找到一个逆序区间（用后面的大数与前面大小数交换）
 * 大数交换到前面后要保证新的序列尽可能小，则需要将大树后面的降序排列
 **/
public class NextPermutation {


    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 找到一个可交换的位置i， [i, nums.length-1] 是升序的
        while (i >= 0 && nums[i+1] <= nums[i]) {
            i--;
        }
        // 找到一个尽可能小的j， 满足下一个更大排列
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= i && nums[j] <= nums[i]) {
                j--;
            }
            // 交换i，j （这里的j就是大数）
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        // 将大数后面降序排列
        int start = i + 1;
        int end = nums.length - 1;
        while (start < end) {
            int help = nums[start];
            nums[start] = nums[end];
            nums[end] = help;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        nextPermutation(nums);
    }
}
