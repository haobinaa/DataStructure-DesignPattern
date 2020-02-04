package com.haobin.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author HaoBin
 * @Create 2020/2/1 18:30
 * @Description: 全排列
 * <p>
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 **/
public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        // 首先是特判
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }
        // 保存已经使用过那些位置
        boolean[] used = new boolean[len];
        // 状态变量，已经进入的状态
        List<Integer> path = new ArrayList<>();
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     * 深度优先遍历
     */
    private void dfs(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        // 退出条件，已经完成了所有的数
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            // 选择下一个没被选的
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used, res);
                // 注意：这里是状态重置，是从深层结点回到浅层结点的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(depth);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permute solution = new Permute();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }
}
