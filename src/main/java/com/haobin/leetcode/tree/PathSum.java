package com.haobin.leetcode.tree;

import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;

/**
 * @Author HaoBin
 * @Create 2020/1/19 9:43
 * @Description: 路径总和
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 **/
public class PathSum {

    /**
     * 问题转换成两个子问题->
     * 1.找出以根节点为开始，任意节点可作为结束，且路径上的节点和为 sum 的路径的个数
     * 2. 然后将每个节点都作为根节点
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 以每个节点都为根节点(对每个节点调用 path方法)，计算路径总和
        return path(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    /**
     * 以当前节点为根节点，找到结果为sum
     */
    private int path(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        if (sum == root.val) {
            ans++;
        }
        ans += path(root.left, sum - root.val);
        ans += path(root.right, sum - root.val);
        return ans;
    }
}
