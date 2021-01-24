package com.haobin.leetcode.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个数组， 编写函数计算它们的交集
 *
 * eg1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 *
 * eg2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 *
 *
 * 说明：
 * 1. 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致
 * 2. 我们可以不考虑输出结果的顺序
 * ===========================
 *
 * 进阶考虑:
 * 如果给定的数组已经排好序呢？将如何优化你的算法呢
 *
 * ===========
 *
 * 思路:
 *
 * 设定两个为0的指针，比较两个指针的元素是否相等。如果指针的元素相等，我们将两个指针一起向后移动，并且将相等的元素放入空白数组
 *
 * @Date 2021/1/24 3:04 下午
 * @author: leobhao
 */
public class TwoArrayIntersection {


    /**
     * hash 映射
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] result = new int[nums1.length];
        int k = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                result[k] = num;
                map.put(num, map.get(num) -1);
                k++;
            }
        }
        return Arrays.copyOfRange(result, 0 , k);
    }

    /**
     * 排序数组双指针
     * 1. 如果两数相同， 放入result， 分别后移一位
     * 2. 如果两数不同， 较小的后移一位
     * 3. 重复上述步骤， 直至一指针到尾
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] result = new int[nums1.length];
        int j = 0, k = 0, index = 0;
        while (j < nums1.length && k < nums2.length) {
            if (nums1[j] == nums2[k]) {
                result[index] = nums1[j];
                j++;
                k++;
                index++;
            } else if (nums1[j] < nums2[k]) {
                j++;
            } else {
                k++;
            }
        }
        return Arrays.copyOfRange(result, 0 , index);
    }

}
