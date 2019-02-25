/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.datastructure.linkedlist;

/**
 * 反转链表
 *
 * @author HaoBin
 * @version $Id: ReserveList.java, v0.1 2019/2/14 9:30 HaoBin
 */
public class ReverseList {

    public static class ListNode {

        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public ListNode reverseListNode(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            next = head.next;
            // 反转
            head.next = pre;
            pre = head;
            // 往下走
            head = next;
        }
        // 尾节点就是新的头结点
        return pre;
    }

    public static void main(String[] args) {

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        new ReverseList().reverseListNode(a);
        while (e != null) {
            System.out.println(e.value);
            e = e.next;
        }
    }
}