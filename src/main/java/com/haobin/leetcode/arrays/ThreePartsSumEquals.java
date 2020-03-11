package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/3/11 14:30
 * @Description: 分割数组成相等的三部分
 *
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 *
 * 形式上，如果可以找出索引 i+1 < j 且满足 
 * (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 
 * 就可以将数组三等分。
 *
 *
 * 示例 1：
 *
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 *
 *
 **/
public class ThreePartsSumEquals {


    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for(int i : A) {
            sum += i;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int left = 0;
        int leftSum = A[left];
        int right = A.length - 1;
        int rightSum = A[right];
        // left + 1 是为了防止数组只被分成两部分
        while (left + 1 < right) {
            if (leftSum == sum/3 && rightSum == sum/3) {
                return true;
            }
            if (leftSum != sum/3){
                left++;
                leftSum += A[left];
            }
            if (rightSum != sum/3) {
                right--;
                rightSum += A[right];
            }
        }
        return false;
    }
}
