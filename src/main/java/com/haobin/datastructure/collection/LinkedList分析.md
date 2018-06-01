### 1.概览
LinkedList基于双向链表的实现，内部使用Node存储节点信息：
![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/LinkedList.jpg)
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

### 2.添加
尾插法添加节点
``` 
public boolean add(E e) {
    linkLast(e);
    return true;
}
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    // 尾指针指向新增的节点
    last = newNode;
    // 如果链表为空，新节点也是头结点
    if (l == null)
        first = newNode;
    else
    // 将新节点作为原来尾节点的后继节点
        l.next = newNode;
    // 长度和修改次数+1
    size++;
    modCount++;
}
```
删除跟普通的链表删除一样，添加到指定索引的时候，需要先找到位置：
``` 
public void add(int index, E element) {
    checkPositionIndex(index);

    if (index == size)
        linkLast(element);
    else
        linkBefore(element, node(index));
}
Node<E> node(int index) {
// 如果是前半段，则从头结点开始找
    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
// 后半段则从尾节点开始找
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}
```