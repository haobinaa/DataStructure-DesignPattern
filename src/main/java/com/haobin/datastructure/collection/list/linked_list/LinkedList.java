package com.haobin.datastructure.collection.list.linked_list;

/**
 * 单向链表
 * @Author: HaoBin
 * @Date 2018/1/26 18:12
 */
public class LinkedList<T> {
    Node<T> head;

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void show() {
        Node<T> temp = this.head;
        while (temp != null) {
            System.out.print(temp.data + ",");
            temp = temp.next;
        }
    }
}
