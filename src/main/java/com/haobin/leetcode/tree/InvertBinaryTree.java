package com.haobin.leetcode.tree;

import com.haobin.datastructure.tree.Tree;
import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;

/**
 * @Author HaoBin
 * @Create 2020/1/19 9:26
 * @Description: 反转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 **/
public class InvertBinaryTree {

    /**
     * 递归解法
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}
