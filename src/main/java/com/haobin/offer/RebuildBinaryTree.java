package com.haobin.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: HaoBin
 * @create: 2019/9/20 10:09
 * @description: 重建二叉树
 * 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字
 * eg:
 * preorder(前序): 3,9,20,15,7
 * inorder(中序): 9,3,15,20,7
 *
 * 构建出该二叉树
 **/
public class RebuildBinaryTree {


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * 用前序遍历的第一个值(根节点)，将中序遍历分为两部分
     * 左边就是左子树中序遍历结果， 右边是右子树中序遍历结果
     * @param args
     */
    public static void main(String[] args) {

    }


    // 中序遍历每个值对应的索引
    private Map<Integer, Integer> indexForInOrder = new HashMap<>();


    /**
     * @param pre 前序遍历结果
     * @param in 中序遍历结果
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrder.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    /**
     * @param pre 前序结果
     * @param preL 前序结果的左边界
     * @param preR 前序右边界
     * @param inL 中序左边界
     */
    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        // 获取根节点
        TreeNode root = new TreeNode(pre[preL]);
        // 根节点在中序结果中的索引
        int inIndex = indexForInOrder.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }
}
