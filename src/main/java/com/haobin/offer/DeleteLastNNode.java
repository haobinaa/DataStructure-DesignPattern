package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/14 14:25
 * @description: 删除倒数第N个节点
 **/
public class DeleteLastNNode {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode tail = head;
        for (int i = 2; i < 6; i++) {
            ListNode node = new ListNode(i);
            tail.next = node;
            tail = node;
            tail.next = null;
        }
        head = deleteLast(head, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode deleteLast(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode pHead = head;
        ListNode first = head;
        ListNode second = head;
        // 保持n个间隔
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 第一个指针指向尾部，则第二个指针指向待删除节点的前一个节点
        second.next = second.next.next;
        return pHead;
    }
}
