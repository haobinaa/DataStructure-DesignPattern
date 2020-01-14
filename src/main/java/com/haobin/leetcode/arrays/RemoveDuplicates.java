package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/1/14 15:34
 * @Description: 删除排序数组中重复元素
 *
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 **/
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }


    /**
     * 快慢指针去重
     * 1. i, j分别为快慢指针
     * 2. 如果 num[i] == num[j] 则跳过
     * 3. 如果 num[i] != num[j] 则 num[j] 赋值给 num[i] 的下一位 并 i++
     * 4. 遍历完成后返回 i 指向数组的最后一个元素， 返回 i+1 即数组长度
     *
     * 与之类似的题： 27.移除元素(移除数组中指定元素)
     */
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = i+1; j < nums.length; j++) {
            if (nums[i] == nums[j]) {
                continue;
            } else {
                nums[i+1] = nums[j];
                i++;
            }
        }
        return i+1;
    }
}
