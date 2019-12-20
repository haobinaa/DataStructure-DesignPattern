package com.haobin.datastructure.tree;

/**
 * @Author HaoBin
 * @Create 2019/12/20 16:23
 * @Description: 二叉树查找树
 **/
public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    /**
     * 根节点
     */
    protected BinaryNode<T> root;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public String preOrder() {
        return null;
    }

    @Override
    public String inOrder() {
        return null;
    }

    @Override
    public String afterOrder() {
        return null;
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public void insert(T data) {
        if (data == null) {
            throw new RuntimeException("data can't be null");
        }
        insert(data, this.root);
    }

    private BinaryNode<T> insert(T data, BinaryNode<T> p) {
        int compareResult = data.compareTo(p.data);
        if (compareResult < 0) {
            p.left = insert(data, p.left);
        } else if (compareResult > 0) {
            p.right = insert(data, p.right);
        }
        return p;
    }

    @Override
    public void remove(T data) {
        if (data == null) {
            throw new RuntimeException("data can't be null");
        }
        remove(data, root);
    }

    /**
     * 1. 如果是叶子节点则直接删除
     * 2. 拥有一个子节点，则直接用孩子节点替换
     * 3. 有两个子节点，找到右子树最小节点替换
     */
    private BinaryNode<T> remove(T data, BinaryNode<T> p) {
        if (p == null) {
            return p;
        }
        int compareResult = data.compareTo(p.data);
        if (compareResult < 0) {
            p.left = remove(data, p.left);
        } else if (compareResult > 0) {
            p.right = remove(data, p.right);
        } else if (p.left != null && p.right != null) { // 两个子节点情况
            // 右子树的最小元素来替换(中继替换)
            p.data = findMin(p.right).data;
            // 移除节点
            p.right = remove(p.data, p.right);
        } else {   // 一个子节点情况
            p = (p.left != null) ? p.left : p.right;
        }
        return p;
    }

    @Override
    public T findMin() {
        return findMin(root).data;
    }

    /**
     * 找到最小的节点，即左子树最小值
     */
    private BinaryNode<T> findMin(BinaryNode<T> p) {
        if (p == null) {
            // 结束条件
            return null;
        } else if (p.left == null) {
            // 若没有左节点，则就是最小节点
            return p;
        }
        return findMin(p.left);
    }

    @Override
    public T findMax() {
        return findMax(root).data;
    }

    /**
     * 最大节点即右子树最大值
     */
    private BinaryNode<T> findMax(BinaryNode<T> p) {
        if (p == null) {
            return null;
        } else if (p.right == null) {
            return p;
        }
        return findMax(p.right);
    }

    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }
}
