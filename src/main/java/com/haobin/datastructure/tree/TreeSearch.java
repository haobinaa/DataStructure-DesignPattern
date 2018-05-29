package com.haobin.datastructure.tree;

/**
 * 树的遍历
 * @Author: HaoBin
 * @Date 2018/1/25 11:53
 */
public class TreeSearch<T> {


    StringBuffer searchPath = new StringBuffer();
    private boolean isSearched = false;


    /**
     * 遍历路径
     * @param root
     * @param data
     */
    public void searchPath(TreeNode<T> root, T data) {
        if(!isSearched){
            if(!searchPath.toString().equals("")){
                searchPath.append("->");
            }
            searchPath.append(root.data);
            if(root.data.equals(data))
                isSearched = true;
        }
    }

    /**
     * 前序遍历root查询item
     * @return
     */
    public void preorderTraversal(TreeNode<T> root, T data){
        if(root == null){
            return;
        }
        searchPath(root, data);
        if(!isSearched)
            preorderTraversal(root.left, data);
        if(!isSearched)
            preorderTraversal(root.right, data);
    }

    /**
     * 中序遍历
     * @param root
     * @param data
     */
    public void inorderTraversal(TreeNode<T> root, T data){
        if(root == null){
            return;
        }

        if(!isSearched)
            inorderTraversal(root.left, data);
        searchPath(root, data);
        if(!isSearched)
            inorderTraversal(root.right, data);
    }


    /**
     * 后序遍历
     * @param root
     * @param data
     */
    public void afterorderTraversal(TreeNode<T> root, T data){
        if(root == null){
            return;
        }

        if(!isSearched)
            afterorderTraversal(root.left, data);

        if(!isSearched)
            afterorderTraversal(root.right, data);
        searchPath(root, data);
    }
}
