package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/10 17:16
 * @description: 删除链表中重复节点，重复节点不保留
 *
 * 例如:
 * 链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 *
 **/
public class DeleteDuplicationNode {

    static class ListNode {
        int val;
        ListNode next;
    }

    public ListNode deleteDuplication(ListNode head) {
        ListNode next = head.next;
        if (head.val == next.val) {
            // 如果头节点是重复节点，则需要删除头节点
            while (next != null && head.val == next.val) {
                next = next.next;
            }
            return deleteDuplication(next);
        } else {
            // 遍历每个节点
            head.next = deleteDuplication(head.next);
            // 返回头节点
            return head;
        }
    }
}
