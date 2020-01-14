package com.haobin.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author HaoBin
 * @Create 2020/1/9 18:43
 * @Description: 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 **/
public class ThreeSum {

    /**
     * 暴力解法
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i+1; j < nums.length-1; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                       List<Integer> resolveList = new ArrayList<>();
                       resolveList.add(nums[i]);
                       resolveList.add(nums[j]);
                       resolveList.add(nums[k]);
                       result.add(resolveList);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 1. 排序
     * 2. 遍历数组
     * 2.1 令左右指针分指向数组两端， L 指向 i+1 R 指向 num.length-1, 然后计算三数之和 sum
     * 2.1.1 若 num[i] > 0 则必不可能在后面出现三数之和为0(已经是有序数组了)
     * 2.1.2 若 num[i] == num[i-1], 则说明数字重复， 会导致结果重复， 跳过
     * 2.1.3 若 sum == 0, num[L] == num[L+1] 会导致结果重复，应该跳过, L++
     * 2.1.4 若 sum == 0, num[R] == num[R-1] 会导致结果重复, 应跳过， R--
     */
    public  List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            // 重复
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length-1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l<r && nums[l] == nums[l+1]) {
                        l++;
                    }
                    l++;
                    while (l<r && nums[r] == nums[r-1]) {
                        r--;
                    }
                    r--;
                } else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,0,0,2,2};
        ThreeSum threeSum = new ThreeSum();
        threeSum.threeSum(nums).stream().forEach((list) -> {
            System.out.println(Arrays.toString(list.toArray()));
        });
    }

}
