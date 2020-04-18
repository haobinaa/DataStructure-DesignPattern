package com.haobin.leetcode.arrays;

/**
 * @Author haobin
 * @Date 2020/4/18 11:07 下午
 * @Description
 *
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 **/
public class MinEatingSpeed {

    public int minEatingSpeed(int[] piles, int H) {
        // 二分法搜索
        int left = 1, right = getMax(piles) + 1;
        while (left < right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 是否能吃完
     * @param piles 香蕉堆
     * @param speed 速度 K
     * @param H 警卫离开时间 H
     */
    boolean canFinish(int[] piles, int speed, int H) {
        long time = 0;
        for (long n : piles) {
            time += timeOf((int) n, speed);
        }
        return time <= H;
    }

    /**
     * 多久能吃完
     * @param n 堆中香蕉数量
     * @param speed 速度 K
     */
    int timeOf(int n, int speed) {
        // 需要几小时+1小时是否吃完
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }

    /**
     * 数组中最大值
     */
    int getMax(int[] piles) {
        int max = 0;
        for (int n : piles)
            max = Math.max(n, max);
        return max;
    }

    public static void main(String[] args) {
        MinEatingSpeed m = new MinEatingSpeed();
        int[] pilse = new int[]{332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
        int H = 823855818;
        System.out.println(m.minEatingSpeed(pilse, H));
    }
}
