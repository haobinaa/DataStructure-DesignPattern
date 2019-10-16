- [快慢指针应用](https://blog.csdn.net/qq_21815981/article/details/79833976)
- [判断链表中环的入口](http://www.nowamagic.net/librarys/veda/detail/1842)

### 目录

- 判断链表是否有环
- 判断链表是否有环，有环则找到出口
- 有序链表中的中位数

### 判断链表是否有环

如果链表有环，就像的跑道一样是一个环形的，使用两个指针。一个一次移动1格，一个一次移动2格，如果快指针到达null,则无环，如果快指针追上慢指针，则有环
``` 
bool HasCircle(Node *head)
{
  if(head == NULL)
    return false;
  Node slow = head, fast = head;
  while(fast != NULL && fast->next!=NULL)
  {
     slow = slow->next; //慢指针每次前进一步
     fast = fast->next->next;//快指针每次前进两步
     if(slow == fast) //相遇，存在环
         return true;
  }
  return false;
}
```

### 判断链表中是否有环，如果有环则找到出口

如果链表存在环，如何找到环的入口点？当fast若与slow相遇时，slow肯定没有走遍历完链表或者恰好遍历一圈。于是我们从链表头与相遇点分别设一个指针，每次各走一步，两个指针必定相遇，且相遇第一点为环入口点。



### 有序链表中寻找中位数
