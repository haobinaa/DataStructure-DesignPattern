package com.haobin.leetcode21;

import com.haobin.leetcode21.structure.ListNode;

/**
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * @Date 2022/6/14 11:28 AM
 * @author: leobhao
 */
public class D5RemoveNthFromEnd {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        if (fast.next == null) {
            return null;
        }
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
