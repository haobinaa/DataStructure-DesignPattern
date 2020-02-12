package com.haobin.leetcode.stack;

import java.util.Stack;

/**
 * @Author HaoBin
 * @Create 2020/2/12 17:12
 * @Description: 每日温度
 *
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 **/
public class DailyTemperature {


    /**
     * 用一个栈保存逆序遍历的位置
     * 遍历时与当前栈顶比较，如果小于当前温度，则出栈
     * 栈空代表当前温度最高，否则记录栈的位置-当前坐标
     *
     * 之前会疑惑出栈了的，前面的元素就无法比较到了，因为是比较高的温度
     * 出栈了代表肯定有温度比出栈的元素高，位置前，不需要保留了
     */
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        //从后往前遍历
        for (int i = T.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek()-i;
            stack.push(i);
        }
        return ans;
    }
}
