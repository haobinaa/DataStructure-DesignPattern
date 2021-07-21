package com.haobin.datastructure.tree;

import java.io.Serializable;

/**
 * @Author HaoBin
 * @Create 2019/12/20 16:36
 * @Description: 二叉树节点
 **/
public class BinaryTreeNode<T extends Comparable> implements Serializable {
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public T data;

    public BinaryTreeNode(BinaryTreeNode<T> left, BinaryTreeNode<T> right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryTreeNode(T data) {
        this.data = data;
    }

    /**
     * 判断是否为叶子节点
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
