package com.haobin.leetcode.arrays;

/**
 * @Author HaoBin
 * @Create 2020/1/19 16:19
 * @Description: 盛水最多的容器
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 题目还有图片，信息在:https://leetcode-cn.com/problems/container-with-most-water/
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 **/
public class MaxArea {

    /**
     * 双指针解法：每次选定围成水槽两板高度 h[i],h[j] 中的短板，向中间收窄 1 格
     * 1. 设每一状态下水槽面积为 S(i,j) (0 <=i<j< n)，由于水槽的实际高度由两板中的短板决定
     * 则可得面积公式 S(i,j) = min(h[i],h[j])×(j−i)
     * 2. 在每一个状态下，无论长板或短板收窄 11 格，都会导致水槽 底边宽度 -1−1：
     *  - 若向内移动短板，水槽的短板 min(h[i], h[j]) 可能变大，因此水槽面积 S(i, j)可能增大
     *  - 若向内移动长板，水槽的短板 min(h[i], h[j]) 不变或变小，下个水槽的面积一定小于当前水槽面积
     *  => 推出向内移动短板可以获取最大面积
     */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int ans = 0;
        while (i < j) {
            int s = (j - i) * Math.min(height[i], height[j]);
            ans = Math.max(s, ans);
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }
}
