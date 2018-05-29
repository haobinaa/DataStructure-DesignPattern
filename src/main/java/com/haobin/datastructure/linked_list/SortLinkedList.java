package com.haobin.datastructure.linked_list;


/**
 * 给出一个值,将链表中左边数据都小于此值,右边的值都大于等于此值
 * 思路：分成个链表，然后连起来就好了
 * @Author: HaoBin
 * @Date 2018/1/26 17:57
 */
public class SortLinkedList {
    /**
     * 方法一 较低效
     */
    public static Node<Integer> sort(Node<Integer> head, Integer value) {
        if (head == null)
            return null;
        Node<Integer> beforeStart = null;
        Node<Integer> beforeEnd = null;
        Node<Integer> afterStart = null;
        Node<Integer> afterEnd = null;


        // 让给定值左边小于，右边大于
        while (head != null) {
            Node<Integer> next = head.next;
            head.next = null;
            if (head.data < value) {
                if (beforeStart == null) {
                    beforeStart = head;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = head;
                    beforeEnd = head;
                }
            } else {
                if (afterStart == null) {
                    afterStart = head;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = head;
                    afterEnd = head;
                }
            }
            head = next;
        }
        // 连起来
        if (beforeStart != null) {
            beforeEnd.next = afterStart;
            head = beforeStart;
        } else {
            head = afterStart;
        }
        return head;
    }


    /**
     * 方法二 较优
     */
    public static Node<Integer> sortBetter(Node<Integer> head, int value) {
        if (head == null) {
            return head;
        }

        Node<Integer> beforeStart = null;
        Node<Integer> afterStart = null;
        // 不需要指向左边的尾节点和右边的尾节点的方法
        while (head != null) {
            Node<Integer> next = head.next;

            if (head.data < value) {
                head.next = beforeStart;
                beforeStart = head;
            } else {
                head.next = afterStart;
                afterStart = head;
            }
            head = next;
        }

        Node<Integer> newHead = null;
        if (beforeStart == null) {
            // 没有小于给定值，右边第一个节点当头结点
            newHead = afterStart;
        } else {
            // 头结点指向左边第一个节点
            newHead = beforeStart;
            // beforeStart遍历到左边链表的尾部
            while (beforeStart.next != null) {
                beforeStart = beforeStart.next;
            }
            // 连上右边的节点
            beforeStart.next = afterStart;
        }

        return newHead;
    }

    public static void main(String[] args) {

    }
}
