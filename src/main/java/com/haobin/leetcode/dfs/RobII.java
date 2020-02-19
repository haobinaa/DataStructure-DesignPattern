package com.haobin.leetcode.dfs;

import com.haobin.leetcode.tree.SymmetricBinaryTree.TreeNode;
import java.util.HashMap;

/**
 * @Author HaoBin
 * @Create 2020/2/19 10:47
 * @Description: 打家劫舍III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 *
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 **/
public class RobII {


    /**
     * 爷爷节点获取到最大的偷取的钱数:
     * 1. 首先要明确相邻的节点不能偷，也就是爷爷选择偷，儿子就不能偷了，但是孙子可以偷
     * 2. 二叉树只有左右两个孩子，一个爷爷最多2个儿子,4个孙子
     *
     * 所以单节点的钱: 4个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱 哪个组合钱多，就当做当前节点能偷的最大钱数
     * 四个孙子+爷爷的钱 val1 = root.val + rob(root.left.left) + rob(root.left.right) + rob(root.right.left) + rob(root.right.right)
     * 两个儿子的钱: vla2 = rob(root.left) + rob(root.right);
     * 最后的结果是 Max(val1, val2)
     *
     * 这个解法没有用到动态规划中对重复结果的利用，在数组中可以存储上一步的结果
     *
     */
    public int rob1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = root.val;
        if (root.left != null) {
            ans += (rob1(root.left.left) + rob1(root.left.right));
        }
        if (root.right != null) {
            ans += (rob1(root.right.left) + rob1(root.right.right));
        }
        return Math.max(ans, rob1(root.left) + rob1(root.right));
    }

    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return  robInternal(root, memo);
    }

    /**
     * 用 hashmap 来存储中间计算过程
     */
    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }
}
