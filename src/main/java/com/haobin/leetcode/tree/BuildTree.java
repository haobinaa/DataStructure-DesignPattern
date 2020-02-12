package com.haobin.leetcode.tree;

import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;

/**
 * @Author HaoBin
 * @Create 2020/2/12 11:12
 * @Description: 从前序和中序遍历构建二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 **/
public class BuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    /**
     * @param preOrder 先序遍历
     * @param pStart 当前子树跟节点
     * @param pEnd 当前子树最后一个节点
     * @param inOrder 中序遍历
     * @param iStart 当前子树根节点
     * @param iEnd 当前子树最后一个节点
     *
     * 这里 [start, end) 左闭右开
     */
    private TreeNode buildTreeHelper(int[] preOrder, int pStart, int pEnd, int[] inOrder, int iStart, int iEnd) {
        // 先序遍历为空返回
        if (pStart == pEnd) {
            return null;
        }
        // 根节点
        int rootVal = preOrder[pStart];
        TreeNode root = new TreeNode(rootVal);
        // 中序遍历找到根节点
        int inRootIndex = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (rootVal == inOrder[i]) {
                inRootIndex = i;
                break;
            }
        }
        // 找到中序遍历左节点个数
        int leftNum = inRootIndex - iStart;
        // 构建左子树
        root.left = buildTreeHelper(preOrder, pStart+1, pStart+leftNum+1, inOrder, iStart, inRootIndex);
        // 构建右子树
        root.right = buildTreeHelper(preOrder, pStart+leftNum+1, pEnd, inOrder, inRootIndex+1, iEnd);
        return root;
    }

}
