package com.haobin.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author HaoBin
 * @Create 2020/1/15 19:26
 * @Description: 数组中只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 **/
public class SingleNumber {

    /**
     * hash 表解法
     */
    public static int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            //if (count == null) {
            //    count = 1;
            //} else {
            //    count++;
            //}
            count = count == null ? 1 : ++count;
            map.put(nums[i], count);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 位运算
     * 异或的方式(运算方相同则为false, 不同为true )
     * a ^ 0 = a
     * a ^ a = 0
     * =>
     * a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b（交换律和结合律）
     */
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }
}
