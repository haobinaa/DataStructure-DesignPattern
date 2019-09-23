package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/23 11:35
 * @description: 旋转数组中最小的数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素
 * 非递减数组: 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]
 * eg:
 * 原数组 : 1,2,3,4
 * 旋转后: 2,3,4,1 return 1
 **/
public class MinNumInRotateArray {


    /**
     * 思路：
     * 将旋转数组对半分后，就会得到一个包含最小元素的旋转数组和一个非递减排序的数组，如 2,3,4,1切分后:
     * {2, 3}, {4, 1} 两个数组，其中{4, 1是}一个旋转数组， 比之前的旋转数组，范围缩小的一半
     */
    public static void main(String[] args) {

    }

    public int minNumberInRotateArray(int nums[]) {
        if (nums.length == 0) {
            return 0;
        }
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h -1) / 2;
            if (nums[m] <= nums[h])
                h = m;
            else
                l = m + 1;
        }
        return nums[l];
    }
}
