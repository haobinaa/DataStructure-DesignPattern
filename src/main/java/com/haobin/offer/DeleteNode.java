package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/10 11:33
 * @description: O(1) 时间内删除链表节点
 *
 * 解题思路
 * (1) 如果该节点不是尾节点，将下个节点的值赋给该节点， 然后指向下下个节点。时间复杂度为O(1)
 * (2) 如果是尾节点，需要遍历找到前一个节点，并让前一个节点指向null, 时间复杂度为O(n)
 *
 * 要求时间复杂度为1， 该算法的平均时间复杂度计算:
 * 如果进行N次操作， 那么操作节点的次数约为 N-1 + N = 2N -1
 * 其中表示 N-1 个不是尾节点的每个节点以 O(1) 的时间复杂度操作节点的总次数，N 表示 1 个尾节点以 O(N) 的时间复杂度操作节点的总次数
 * (2N-1)/N ~ 2， 平均时间复杂度为O(1)
 **/
public class DeleteNode {
    static class ListNode {
        int val;
        ListNode next;
    }

    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (tobeDelete.next != null) {
            // 被删除节点不是尾节点
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else {
            // 如果是尾节点
            ListNode cur = head;
            while (cur.next != tobeDelete) {
                cur = cur.next;
            }
            cur.next = null;
        }
        return head;
    }
}
