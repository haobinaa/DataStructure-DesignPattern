package com.haobin.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author HaoBin
 * @Create 2020/2/14 10:36
 * @Description: 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 **/
public class MergeIntervals {


    /**
     * 1. 按首位置进行排序
     * 2. 当 a[1] > b[0] ， 则表名两个区间重叠
     * 3. 左边区间是 a[0], 右边区间是 max{a[1], b[1]}
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) {
            return ans.toArray(new int[0][]);
        }
        Arrays.sort(intervals, (a, b) -> {return a[0] - b[0];});
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 判断是否含有重叠区间
            while (i < intervals.length-1 && intervals[i+1][0] <= right) {
                // 下一组数属于重叠区间
                i++;
                // 重叠区间的右边界
                right = Math.max(right, intervals[i][1]);
            }
            ans.add(new int[]{left, right});
            i++;
        }
        return ans.toArray(new int[0][]);
    }
}
