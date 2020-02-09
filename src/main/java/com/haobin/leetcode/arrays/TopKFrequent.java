package com.haobin.leetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author HaoBin
 * @Create 2020/2/7 22:06
 * @Description: 前K个高频元素
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 **/
public class TopKFrequent {

    /**
     * 1. 借助 哈希表 来建立数字和其出现次数的映射，遍历一遍数组统计元素的频率
     * 2. 维护一个元素数目为 k 的最小堆
     * 3. 每次都将新的元素与堆顶元素（堆中频率最小的元素）进行比较
     * 4. 如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
     * 5. 最终，堆中的 k 个元素即为前 k 个高频元素
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> map.get(a) - map.get(b));
        for(Integer key : map.keySet()) {
            if (minHeap.size() < k) {
                minHeap.add(key);
            } else if (map.get(key) > map.get(minHeap.peek())){
                minHeap.remove();
                minHeap.add(key);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ans.add(minHeap.remove());
        }
        return ans;
    }
}
