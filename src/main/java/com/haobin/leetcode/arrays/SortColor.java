package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/2/12 22:35
 * @Description: 颜色分类
 **/
public class SortColor {


    /**
     * 用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素
     * 沿着数组移动 curr 指针，若nums[curr] = 0，则将其与 nums[p0]互换；
     * 若 nums[curr] = 2 ，则与 nums[p2]互换。
     */
    public void sortColors(int[] nums) {
        int p0 = 0;
        int current = 0;
        int p2 = nums.length-1;
        int temp;
        while (current <= p2) {
            if (nums[current] == 0) {
                temp = nums[current];
                nums[current] = nums[p0];
                nums[p0] = temp;
                p0++;
                current++;
            } else if (nums[current] == 2) {
                temp = nums[current];
                nums[current] = nums[p2];
                nums[p2] = temp;
                p2--;
            } else {
                current++;
            }
        }
    }
}
