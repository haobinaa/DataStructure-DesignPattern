package com.haobin.leetcode.tree;

import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;

/**
 * @Author HaoBin
 * @Create 2020/1/19 11:34
 * @Description: 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表
 *
 **/
public class DiameterOfBinaryTree {

    int max = 0;

    /**
     * 思路： 对于每个节点来说，直径 = 左子树深度 + 右子树深度
     * 那就遍历每个节点，找出最大深度
     * 使用深度优先搜索
     */
    public int diameterOfBinaryTree(TreeNode root) {
       depth(root);
       return max;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int L = depth(root.left);
        int R = depth(root.right);
        // 保存节点中最大的直径
        max = Math.max(L + R, max);
        return Math.max(L, R) + 1;
    }
}
