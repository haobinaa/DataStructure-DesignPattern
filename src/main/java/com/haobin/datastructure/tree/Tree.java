package com.haobin.datastructure.tree;

/**
 * 二叉树的定义
 * @param <T>
 */
public interface Tree<T extends Comparable> {

    /**
     * 判空
     */
    boolean isEmpty();

    /**
     * 结点数
     */
    int size();

    /**
     * 二叉树的高度
     */
    int height();

    /**
     * 先根遍历
     */
    String preOrder();

    /**
     * 中根
     */
    String inOrder();


    /**
     * 后根
     */
    String afterOrder();

    /**
     * 层次遍历
     */
    String levelOrder();

    /**
     * 插入
     */
    void insert(T data);

    /**
     * 删除
     */
    void remove(T data);


    /**
     * 最小值
     */
    T findMin();

    /**
     * 最大值
     */
    T findMax();

    /**
     * 找到节点
     */
    BinaryNode findNode(T data);

    /**
     * 是否包含某个值
     * @param data
     * @return
     * @throws Exception
     */
    boolean contains(T data) throws Exception;
}
