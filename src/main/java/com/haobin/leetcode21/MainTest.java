package com.haobin.leetcode21;

/**
 * @Date 2022/6/2 8:38 AM
 * @author: leobhao
 */
public class MainTest {


    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l >= r) {
            int mid = (l+r) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l+r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l+1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        search(nums, 9);
    }


}
