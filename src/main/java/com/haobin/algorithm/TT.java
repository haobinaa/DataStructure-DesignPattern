/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm;

/**
 * @author HaoBin
 * @version $Id: TT.java, v0.1 2019/2/28 12:42 HaoBin
 */
public class TT {

    public static ListNode reverseList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        head.next = null;
        return pre;
    }

    public static int removeElement(int[] nums, int val) {
        int oldLength = nums.length;
        int i = 0;
        while (i < oldLength) {
            if (nums[i] == val) {
                for (int j=i+1; j<oldLength;j++) {
                    nums[j-1] = nums[j];
                }
                oldLength--;
            }else {
                i++;
            }
        }
        return oldLength;
    }

    public static void main(String[] args) {


    }

    public static int lengthOfLastWord(String s) {
        if (s.equals("") || s.equals(" "))
            return 0;
        String[] arr = s.split(" ");
        if (arr.length == 0)
            return 0;
        return arr[arr.length-1].length();
    }
    static class ListNode {

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}