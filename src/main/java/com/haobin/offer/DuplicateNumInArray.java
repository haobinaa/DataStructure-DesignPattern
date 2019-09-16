package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/16 8:51
 * @description: 3-数组中重复数字
 *
 * 题目描述：
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的,
 * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字
 **/
public class DuplicateNumInArray {


    /**
     * 思路： 已知数组范围都在 [0, n-1]
     * 将值为 i 的元素调整到第 i 个位置上进行求解
     * 若第i个位置上已经有值为i的数了，可知i重复
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5};
        int[] duplicatuon = new int[1];
        if (duplicate(nums, duplicatuon)) {
            System.out.println(duplicatuon[0]);
        }

    }

    /**
     * 返回任意重复的一个，赋值duplication[0]
     */
    public static boolean duplicate(int[] num, int[] duplication) {
        if (num.length <= 0) {
            return false;
        }
        for (int i = 0; i < num.length; i++) {
            while (num[i] != i) {
                if (num[i] == num[num[i]]) {
                    duplication[0] = num[i];
                    return true;
                }
                swap(num, i, num[i]);
            }
        }
        return false;
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
