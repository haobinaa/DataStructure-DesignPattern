package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/2/24 11:39
 * @Description: 分割等和子集
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 **/
public class CanPartition {


    /**
     * 状态定义：dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数，每个数只能用一次，使得这些数的和恰好等于 j
     * 状态转移:
     * (1) 不选择 nums[i]，如果在 [0, i - 1] 这个子区间内已经有一部分元素，使得它们的和为 j ，那么 dp[i][j] = true
     * (2) 选择 nums[i]，如果在 [0, i - 1] 这个子区间内就得找到一部分元素，使得它们的和为 j - nums[i]
     * 得出状态转移方程: dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i]]
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        // 奇数就不符合要求(无法分成两部分)
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum/2;
        boolean[][] dp = new boolean[len][target+1];
        if (nums[0] < target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }
}
