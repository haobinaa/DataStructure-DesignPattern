package io.haobin.tree;

/**
 *
 * 树的节点
 * @Author: HaoBin
 * @Date 2018/1/25 11:52
 */
public class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
    }
}
