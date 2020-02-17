package com.haobin.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @Author haobin
 * @Date 2020/2/4 11:26 下午
 * @Description: 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 **/
public class CombinationSum {

    private List<List<Integer>> ans = new ArrayList<>();
    private int[] candidates;
    private int len;

    private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
        if (residue == 0) {
            ans.add(new ArrayList<>(pre));
            return;
        }
        // 剩余的数小于0,代表没必要进行下去了
        for (int i = start; i < len && residue - candidates[i] > 0; i++) {
            pre.add(candidates[i]);
            // 回溯
            findCombinationSum(residue-candidates[i], i, pre);
            pre.pop();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return ans;
        }
        // 排序顺便去重
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return null;
    }
}
