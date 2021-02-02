package com.haobin.leetcode.linkedlist;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * @Author HaoBin
 * @Create 2020/1/15 15:38
 * @Description: 反转链表
 **/
public class ReverseList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static ListNode buildList() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node1;
    }


    /**
     * 不借助其他工具，如栈
     * 迭代法
     * 使用两个指针，使当前链表指针指向前一个节点
     * 需要暂时保存当前节点下一个节点的位置
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }


    /**
     * 递归解法
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static ListNode successor = null;

    /**
     * 反转前N个链表
     * @param head
     * @param n
     * @return
     */
    public static ListNode reverseNList(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseNList(head.next, n-1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * 反转链表的一一部分
     * 给一个索引区间 [m,n]（索引从 1 开始），仅仅反转区间中的链表元素。
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            // 如果m=1， 相当于反转前 n 个元素
            return reverseNList(head, n);
        }
        // 将 head.next 的索引设为1， 一直往 base case 前进
        head.next = reverseBetween(head.next, m-1 ,n-1);
        return head;
    }

    public static void main(String[] args) {
        ListNode head = buildList();
//        head = reverseList(head);
        head =reverseNList(head, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
