package com.haobin.leetcode.arrays;

import com.haobin.leetcode.arrays.TwoAdd.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author HaoBin
 * @Create 2020/2/1 16:58
 * @Description: 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 **/
public class SubSet {


    /**
     * 枚举法
     * 每次把当前元素加入现有集合
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 空集
        ans.add(new ArrayList<Integer>());
        for (int n : nums) {
            int size = ans.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(ans.get(i));
                newSub.add(n);
                ans.add(newSub);
            }
        }
        return ans;
    }

    /**
     * DFS 遍历
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        // todo 回溯解法
        return null;
    }
}
