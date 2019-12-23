package com.haobin.datastructure.tree;

/**
 * @Author HaoBin
 * @Create 2019/12/23 14:48
 * @Description: 平衡二叉树节点
 **/
public class AVLNode<T extends Comparable> {

    public AVLNode<T> left;
    public AVLNode<T> right;

    public T data;
    // 节点高度(空节点高度为-1， 叶子节点高度为0)
    public int height;

    public AVLNode(T data) {
        this.data = data;
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
        this.left = left;
        this.right = right;
        this.data = data;
        this.height = height;
    }
}
