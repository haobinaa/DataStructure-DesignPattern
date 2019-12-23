package com.haobin.datastructure.tree;

/**
 * @Author HaoBin
 * @Create 2019/12/23 14:41
 * @Description: 平衡二叉树
 *
 * 通过旋转操作来修复二叉树的平衡(高度差不超过1)
 **/
public class AVLTree<T extends Comparable> {


    /**
     * 左左单旋(LL)操作
     * 左子树比右子树深两层，通过右向旋转来修复失衡节点
     * @param node 失衡节点
     */
    private AVLNode<T> singleRotateLeft(AVLNode<T> node) {
        // w为右旋出的根节点
        AVLNode<T> w = node.left;
        // w的右子树变成node的左子树
        node.left = w.right;
        // node变成w的右子树
        w.right = node;
        // todo 重新计算 w,x 的高度
        return w;
    }

    /**
     * 右右单旋(RR)操作
     * 右子树比左子树深两层，通过左向旋转来修复失衡节点
     * 即： x 变为 w 的根节点， w 变成 x 的左子树
     * @param node 失衡节点
     */
    private AVLNode<T> singleRotateRight(AVLNode<T> node) {
        // w 为左旋出的根节点
        AVLNode<T> w = node.right;
        // w 的左子树变成node的右子树
        node.right = w.left;
        // node 变成w的左子树
        w.left = node;
        // todo 重新计算高度
        return w;
    }
}
