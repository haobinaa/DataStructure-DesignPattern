package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/1/16 10:58
 * @Description: 移动0
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例: 输入: [0,1,0,3,12] 输出: [1,3,12,0,0]
 **/
public class MoveZeroes {

    /**
     * 冒泡的思路。每次将 0 冒泡到末尾
     */
    public void moveZeroes1(int[] nums) {
        // 当前不为0的结尾
        int length = nums.length;
        for (int i = nums.length - 2; i >= 0; i--) {
            // 如果当前元素为0
            if (nums[i] == 0) {
                // 利用冒泡的思路，冒泡到末尾
                for (int j = i + 1; j < length; j++) {
                    nums[j - 1] = nums[j];
                    nums[j] = 0;
                }
                // 最后已经是0了，记录的长度-1
                length--;
            }
        }
    }

    /**
     * 优化思路
     * 1.遍历数组把非零元素（假设有k个）按顺序存入数组的0至k-1位置上；
     * 2.把原数组剩余未新赋值部分(k到n-1位置上)全设置为0；
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        // 除开非 0 元素剩下的全是0
        for (; index < nums.length; index++) {
            nums[index] = 0;
        }
    }

}
