package com.haobin.datastructure.tree;

/**
 * 检查是否平衡树,此处平衡树的定义是两棵子树的高度差不超过1
 * @Author: HaoBin
 * @Date 2018/1/26 17:21
 */
public class IsBalanceTree {

    /**
     * 返回树的高度
     * @param root
     * @return
     */
    private static int getHeight(TreeNode<Integer> root){
        if(root == null){
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * 检查是否平衡
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode<Integer> root){

        // 退出条件
        if(root == null){
            return true;
        }

        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if(Math.abs(heightDiff) > 1){
            return false;
        }else{
            // 子树是否平衡
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    /**
     * 检查树的高度
     * 子树不平衡则返回-1
     * @param root
     * @return
     */
    private static int checkHeight(TreeNode<Integer> root){
        if(root == null){
            return 0;
        }

        int leftHeight = checkHeight(root.left);
        if(leftHeight == -1){
            return -1;
        }

        int rightHeight = checkHeight(root.right);
        if(rightHeight == -1){
            return -1;
        }

        int heightDiff = leftHeight -  rightHeight;
        if(Math.abs(heightDiff) > 1){
            return -1;
        }else{
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 检查是否平衡
     * @param root
     * @return
     */
    public static boolean isBalancedGood(TreeNode<Integer> root){
        if(checkHeight(root) == -1){
            return false;
        }else{
            return true;
        }
    }
}
