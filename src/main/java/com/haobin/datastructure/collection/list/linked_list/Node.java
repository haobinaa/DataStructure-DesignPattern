package com.haobin.datastructure.collection.list.linked_list;

/**
 * 链表节点
 * @Author: HaoBin
 * @Date 2018/1/26 17:54
 */
public class Node<T> {
    public Node<T> next;
    T data;

    Node (T data) {
        this.data = data;
    }
}
