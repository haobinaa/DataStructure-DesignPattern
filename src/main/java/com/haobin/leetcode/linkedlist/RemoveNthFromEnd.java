package com.haobin.leetcode.linkedlist;

import com.haobin.leetcode.linkedlist.ReverseList.ListNode;

/**
 * @Author HaoBin
 * @Create 2020/1/20 17:49
 * @Description: 删除链表倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 **/
public class RemoveNthFromEnd {


    /**
     * 思路双指针，快指针比慢指针多走N步
     * 当快指针到顶了，此时慢指针指向倒数第n个
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = fast;
        // 预指针
        ListNode pre = slow;
        // head 赋值是一步
        for (int i = 0; i < n-1; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        // 删除头节点
        if (pre == slow) {
            return head.next;
        }
        pre.next = slow.next;
        return head;
    }
}
