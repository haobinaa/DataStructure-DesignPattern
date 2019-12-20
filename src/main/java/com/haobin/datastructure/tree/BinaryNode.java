package com.haobin.datastructure.tree;

import java.io.Serializable;

/**
 * @Author HaoBin
 * @Create 2019/12/20 16:36
 * @Description: 二叉树节点
 **/
public class BinaryNode <T extends Comparable> implements Serializable {
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    public T data;

    public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryNode(T data) {
        this.data = data;
    }

    /**
     * 判断是否为叶子节点
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
