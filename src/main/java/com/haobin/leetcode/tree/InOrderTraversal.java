package com.haobin.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author haobin
 * @Date 2020/2/4 11:15 下午
 * @Description 二叉树中序遍历
 **/
public class InOrderTraversal {

    public List<Integer> inorderTraversal(SymmetricBinaryTree.TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inOrder(root, ans);
        return ans;
    }

    private void inOrder(SymmetricBinaryTree.TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        inOrder(root.left, ans);
        ans.add(root.val);
        inOrder(root.right, ans);
    }
}
