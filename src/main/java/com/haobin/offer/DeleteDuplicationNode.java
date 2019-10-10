package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/10 17:16
 * @description: 删除排序链表中重复节点，重复节点不保留
 *
 * 题目描述:
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 *
 **/
public class DeleteDuplicationNode {

    static class ListNode {
        int val;
        ListNode next;
    }

    public ListNode deleteDuplication(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        // 标记当前重复节点
        if (head.val == node.val) {
            // 找到所有重复的节点
            while (node != null && head.val == node.val) {
                node = node.next;
            }
            // 返回不重复的节点，并往后搜索
            return deleteDuplication(node);
        } else {
            // 如果当前节点不重复，从下一个节点开始搜索
            head.next = deleteDuplication(head.next);
            // 返回头节点
            return head;
        }
    }
}
