package com.haobin.leetcode21;

import java.util.Arrays;

/**
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 *
 * 题解1: 平方后， 排序
 * 题解2：
 * 数组 nums 已经按照升序排序」这个条件。显然，如果数组 nums 中的所有数都是非负数，那么将每个数平方后，数组仍然保持升序；
 * 如果数组 nums 中的所有数都是负数，那么将每个数平方后，数组会保持降序。
 *
 * 这样一来，如果我们能够找到数组 nums 中负数与非负数的分界线，那么就可以用类似「归并排序」的方法了。
 * 设 neg 为数组 nums 中负数与非负数的分界线，也就是说，nums[0] 到 nums[neg} 均为负数，而 nums[neg+1] 到 nums[n-1] 均为非负数。
 * 将数组 nums 中的数平方后，那么 nums[0] 到 nums[neg] 单调递减，nums[neg+1] 到 nums[n-1] 单调递增。
 *
 * 使用两个指针分别指向位置 neg 和 neg+1，每次比较两个指针对应的数，选择较小的那个放入结果并移动指针。当某一指针移至边界时，将另一指针还未遍历到的数依次放入答案。
 *
 *
 *
 * @Date 2022/6/9 9:28 PM
 * @author: leobhao
 */
public class D2SortedSquares {


    public static void main(String[] args) {
        int nums[] = {1};
        System.out.println(Arrays.toString(sortedSquares(nums)));
    }


    public static int[] sortedSquares(int[] nums) {
        int neg = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                neg = i;
            }
            nums[i] = nums[i] * nums[i];
        }
        int[] ans = new int[nums.length];
        int i = neg, j = neg + 1, index = 0;
        while (i >= 0 && j < nums.length) {
            if (nums[i] < nums[j]) {
                ans[index] = nums[i];
                i--;
            } else {
                ans[index] = nums[j];
                j++;
            }
            index++;
        }
        while (i >= 0) {
            ans[index] = nums[i];
            index++;
            i--;
        }
        while (j < nums.length) {
            ans[index] = nums[j];
            index++;
            j++;
        }
        return ans;
    }
}
