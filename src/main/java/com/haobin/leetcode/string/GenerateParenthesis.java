package com.haobin.leetcode.string;

import com.haobin.leetcode.linkedlist.ReverseList.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author HaoBin
 * @Create 2020/1/21 9:27
 * @Description: 生成括号
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 **/
public class GenerateParenthesis {


    /**
     * 深度优先遍历(回溯+剪枝)
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }
        dfs("", n, n, ans);
        return ans;
    }

    /**
     * 深度优先遍历：
     * (1) 生成左枝叶(左括号)条件: 左括号剩余 > 0
     * (2) 生成右枝叶(右括号）条件: 左括号剩余 < 右括号剩余
     * (3  剪枝条件：左括号剩余 > 右括号剩余
     * @param curStr 当前递归的结果
     * @param left 左括号剩余的使用
     * @param right 右括号剩余的使用
     * @param res 结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 剪枝
        if (left > right) {
            return;
        }
        if (left > 0) {
            // 生成左枝
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right > 0) {
            // 生成右枝
            dfs(curStr + ")", left, right - 1, res);
        }
    }
}
