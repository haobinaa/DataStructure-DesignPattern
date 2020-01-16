package com.haobin.leetcode.arrays;

import java.util.Arrays;

/**
 * @Author HaoBin
 * @Create 2020/1/16 9:22
 * @Description: 寻找最短无序子数组
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 *
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 *
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 **/
public class FindUnsortedSubArray {


    /**
     * 无序区间为num[l, r]中 max， min
     * 左区间num[0, r] 都小于 min
     * 右区间[l, n-1] 都大于 max
     */
    public int findUnsortedSubarray1(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[len-1];
        int l = 0, r = -1;
        // 有边界之后的元素都大于max， 找到max位置
        for (int i = 0; i < nums.length; i++) {
            if (max > nums[i]) {
                r = i;
                max = nums[i];
            }
        }
        for (int i = nums.length-1; i >= 0 ; i--) {
            // 左边界都小于 min， 找到 min 位置
            if (min < nums[i]) {
                l = i;
                min = nums[i];
            }
        }
        return r-l+1;
    }

    /**
     * 辅助数组为排序好的
     * 对比两个数组不相同的地方，最左边则为左边界，最右边则为右边界
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] temNums = nums.clone();
        Arrays.sort(nums);
        int start = nums.length;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (temNums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end - start > 0 ? end - start + 1 : 0;
    }
}
