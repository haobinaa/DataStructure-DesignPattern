package com.haobin.leetcode.tree;

import com.haobin.datastructure.tree.Tree;
import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;

/**
 * @Author HaoBin
 * @Create 2020/2/12 14:25
 * @Description: 最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 *最近公共祖先的定义为：
 * 对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 *          3
 *    5            1
 * 6     2      0      8
 *     7   4
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 **/
public class LowestCommonAncestor {

    private TreeNode ans;

    private boolean recurseTreeNode(TreeNode currentNode, TreeNode p, TreeNode q) {
        if (currentNode == null) {
            return false;
        }
        int left = this.recurseTreeNode(currentNode.left, p, q) ? 1 : 0;
        int right = this.recurseTreeNode(currentNode.right, p, q) ? 1 : 0;
        // 如果当前节点是被查找节点之一
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;
        // 如果当前节点包含两个标志位，则代表当前节点是最近的公共父节点
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }
        // 只要一个节点被找到了返回true 否则 false
        return (mid + left + right > 0);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTreeNode(root, p, q);
        return this.ans;
    }
}
