package com.haobin.leetcode.tree;

import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;

/**
 * @Author HaoBin
 * @Create 2020/2/5 15:53
 * @Description: 二叉树展开为链表
 *
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 **/
public class Flatten {


    /**
     * 1 将左子树插到右子树的地方
     * 2 右子树接到左子树的最右节点
     * 3 重复上述过程
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            // 如左子树为空，处理下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 记录左子树
                TreeNode pre = root.left;
                // 找到左子树最右节点
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 右子树插到左子树最右节点
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                // 处理下一个节点
                root = root.right;
            }
        }
    }
}
