package com.haobin.datastructure.tree;

/**
 * 二叉查找树判断：对所有节点来说:所有左子树节点都小于等于其根节点;所有右子树节点都大于其根节点
 * @Author: HaoBin
 * @Date 2018/1/26 17:09
 */
public class IsBinarySearchTree {

    public static boolean checkBST(TreeNode<Integer> root, Integer min, Integer max) {
        if (root == null)
            return false;
        if (root.data > max || root.data < min) {
            return false;
        }
        if (!checkBST(root.left, min, max) || !checkBST(root.right, min, max)) {
            return false;
        }
        return true;
    }
}
