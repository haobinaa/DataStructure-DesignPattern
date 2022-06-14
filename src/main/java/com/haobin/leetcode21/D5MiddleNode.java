package com.haobin.leetcode21;

import com.haobin.leetcode21.structure.ListNode;

/**
 *
 *
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 * @Date 2022/6/14 10:50 AM
 * @author: leobhao
 */
public class D5MiddleNode {


    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
