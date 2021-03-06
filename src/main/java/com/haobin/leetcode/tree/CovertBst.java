package com.haobin.leetcode.tree;

import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;

/**
 * @Author HaoBin
 * @Create 2020/1/19 11:46
 * @Description: 二叉树转累加树
 *
 * 给定一个二叉搜索树（Binary Search Tree）
 * 把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 *
 * 输入: 二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *
 **/
public class CovertBst {

    private int sum = 0;
    /**
     * 二叉搜索树的中序遍历是升序的
     * 反序中序遍历就可以降序遍历，并记录已经遍历过的值
     */
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            convertBST(root.left);
        }
        return root;
    }
}
