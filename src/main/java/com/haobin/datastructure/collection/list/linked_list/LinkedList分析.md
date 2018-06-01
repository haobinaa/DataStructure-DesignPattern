### 1.概览
LinkedList基于双向链表的实现，内部使用Node存储节点信息：
``` 
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```
链表中存储了头尾节点的指针
``` 
    transient Node<E> first;
    transient Node<E> last;
```

![](E:\github_code\DataStructure\images\LinkedList.jpg)