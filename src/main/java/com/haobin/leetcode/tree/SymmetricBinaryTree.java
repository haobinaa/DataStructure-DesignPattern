package com.haobin.leetcode.tree;

/**
 * @Author HaoBin
 * @Create 2020/1/18 14:08
 * @Description: 对称二叉树
 *
 * 检测二叉树是否是对称的
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 **/
public class SymmetricBinaryTree {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 构造另一颗一模一样的树，分别为 t1, t2
     * 若 t1.val = t2.val => t1.left.val = t2.right.val
     * 否则不对称结束递归
     */
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root, root);
    }

    private boolean checkSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        // 短路效应，只有一个为空则不对称
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val)
            && checkSymmetric(t1.left, t2.right)
            && checkSymmetric(t1.right, t2.left);
    }

}
