package com.haobin.leetcode.arrays;

import java.util.Arrays;

/**
 * @Author HaoBin
 * @Create 2020/1/17 11:43
 * @Description: 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 *
 **/
public class MajorElement {

    /**
     * 数组先排序
     * 那么多数元素出现的下标就是 2/n(n为偶数) 或 2/n +1
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        if (nums.length % 2 == 0) {
            return nums[nums.length/2-1];
        } else {
            return nums[nums.length/2];
        }
    }
}
