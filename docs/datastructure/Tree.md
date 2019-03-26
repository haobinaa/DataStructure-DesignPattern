## 树

树的几个定义:

![](../../images/tree/tree.jpg)


### 二叉树

二叉树是每个节点最多有两个子节点的树。

#### 二叉树的存储结构

二叉树的常用存储结构一般是用链表，每个节点有data和指向左右子节点的指针:
![](../../images/tree/binary-tree.jpg)

还有一种是顺序存储法，将二叉树存储在数组中。将根节点存储在下标 i=1 的位置，那么左子节点就是 2*i, 右子节点就是 2*i+1：
![](../../images/tree/binary-array-tree.jpg)

这样就就可以通过 2*i 和 2*i+1 推算出左右子节点， i/2推算出父节点。但是浪费了一下标为0的空间。而且这是一颗完全二叉树，如果是非完全二叉树，将浪费更多的空间

#### 二叉树的遍历

- 前序遍历(先根)：根->左->右
- 中序遍历(中根)：左->根->右
- 后序遍历(后根): 左->右->跟

![](../../images/tree/iterator-binary-tree.jpg)

递推公式如下:
``` 
前序遍历的递推公式：
preOrder(r) = print r->preOrder(r->left)->preOrder(r->right)

中序遍历的递推公式：
inOrder(r) = inOrder(r->left)->print r->inOrder(r->right)

后序遍历的递推公式：
postOrder(r) = postOrder(r->left)->postOrder(r->right)->print r
```

#### 二叉查找树

二叉查找树的一种特殊的二叉树，任一节点的左子节点小于该节点，右子节点大于该节点。

##### 二叉查找树的查找操作

查找的值如果小于当前节点就递归左子树，如果大于当前节点就递归右子树，代码实现如下:
``` 
public class BinarySearchTree {
  private Node tree;

  public Node find(int data) {
    Node p = tree;
    while (p != null) {
      if (data < p.data) p = p.left;
      else if (data > p.data) p = p.right;
      else return p;
    }
    return null;
  }

  public static class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
      this.data = data;
    }
  }
}
```

##### 二叉查找树的插入

BST的插入和查找类似，因为一般的数据都插入到叶子节点上，从根节点开始，如果插入的数据比节点大，并且右子树为空，就插入到当前节点的右子树，比他小并且左子树为空，就插入到左子树上

代码如下:
``` 
public void insert(int data) {
  if (tree == null) {
    tree = new Node(data);
    return;
  }

  Node p = tree;
  while (p != null) {
    if (data > p.data) {
      if (p.right == null) {
        p.right = new Node(data);
        return;
      }
      p = p.right;
    } else { // data < p.data
      if (p.left == null) {
        p.left = new Node(data);
        return;
      }
      p = p.left;
    }
  }
}

```

#### 二叉查找树的删除操作

删除的操作会稍微复杂一点，分三种情况:
1. 如果删除的节点没有子节点，就直接将父节点中，指向删除的节点的指针赋值null
2. 如果要删除的节点只有一个节点(左节点或者右节点)，只需要将父节点指向该节点的指针指向它的子节点即可
3. 如果要删除的节点有两个节点，需要找到这个节点的右子树的最小节点，将他替换到要删除的节点上。在删除这个节点，因为最小节点肯定没有左子节点(因为左子树最小)

三种情况如下图:
![](../../images/tree/delete-bst.jpg)


代码如下:
``` 
public void delete(int data) {
  Node p = tree; // p 指向要删除的节点，初始化指向根节点
  Node pp = null; // pp 记录的是 p 的父节点
  while (p != null && p.data != data) {
    pp = p;
    if (data > p.data) p = p.right;
    else p = p.left;
  }
  if (p == null) return; // 没有找到

  // 要删除的节点有两个子节点
  if (p.left != null && p.right != null) { // 查找右子树中最小节点
    Node minP = p.right;
    Node minPP = p; // minPP 表示 minP 的父节点
    while (minP.left != null) {
      minPP = minP;
      minP = minP.left;
    }
    p.data = minP.data; // 将 minP 的数据替换到 p 中
    p = minP; // 下面就变成了删除 minP 了
    pp = minPP;
  }

  // 删除节点是叶子节点或者仅有一个子节点
  Node child; // p 的子节点
  if (p.left != null) child = p.left;
  else if (p.right != null) child = p.right;
  else child = null;

  if (pp == null) tree = child; // 删除的是根节点
  else if (pp.left == p) pp.left = child;
  else pp.right = child;
}

```

##### 其他操作

找到最大节点(最右的节点):
``` 
public Node findMax() {
  if (tree == null) return null;
  Node p = tree;
  while (p.right != null) {
    p = p.right;
  }
  return p;
}
```
同理找到最小节点只需要找到最左的节点

使用中序遍历可以输出有序的的数据序列


### 红黑树

BST的查找、插入、删除等操作都比较高校。但是在极端情况下BST会退化成链表，达到不平衡的效果。

平衡二叉树是一种左右子树高度差不超过1，防止出现二叉树退化成链表的状态，保持平衡。