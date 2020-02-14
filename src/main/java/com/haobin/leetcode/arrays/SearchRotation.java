package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/2/14 14:01
 * @Description: 搜索旋转数组
 *
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 *  示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 **/
public class SearchRotation {


    /**
     * 对于这种情况的二分查找(升序并旋转)， 折半判断有序区间
     * 1. 10111 和 11101 这种。此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，此时 start++ 即可。
     * 相当于去掉一个重复的干扰项。
     *
     * 2. 2 3 4 5 6 7 1 这种，也就是 nums[start] < nums[mid]。此例子中就是 2 < 5；
     * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
     *
     *
     * 3. 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2；
     * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，否则去前半部分找
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        int mid;
        while (start <= end) {
            // 二分
            mid = start + (end - start)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            // 前半部分有序
            if (nums[start] < nums[mid]) {
                // 前半部分找
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {
                    // 后半部找
                    start = mid + 1;
                }
            } else {
                // 后半部分有序
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
