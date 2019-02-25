## 排序算法总结

常用的排序算法时间复杂度:
![](../../images/sort/time-complex.jpg)

### 各种排序算法的实现

#### 冒泡排序

冒泡排序每次操作相邻两个元素，看是否满足大小关系，如果不满足就两者互换，一次排序会让至少一个元素移动到它应该在的位置，重复n次，就完成了n个元素排序的工作

现有`4, 5, 6, 3, 2, 1`， 冒泡排序操作如下:
![](../../images/sort/buble-sort.jpg)

代码实现： [冒泡排序代码实现](../../src/main/java/com/haobin/datastructure/sort/BubbleSort.java)

时间复杂度分析:
- 最好情况：数据已经是有序的，此时时间复杂度为 O(n)
- 最坏情况: 每次都需要冒泡到尾部， 此时时间复杂度是两个for循环，为 O(n^2)

#### 插入排序

插入排序的左侧是一个有序区间，右侧是无序区间，将无序区间的元素在已排序的区间找到合适地方将其插入，保证有序区间一直有序，重复这个过程，直到无序区间元素为空。

现有`4, 5, 6, 3, 2, 1`， 插入排序操作如下:
![](../../images/sort/insert-sort.jpg)

代码实现： [插入排序代码实现](../../src/main/java/com/haobin/datastructure/sort/InsertSort.java)

时间复杂度分析:
- 最好情况，排序数据已经有序，不需要搬移任何数据，每次只需要比较一次就能插入，此时时间复杂度为 O(n)
- 最坏情况：数组如果是倒序，每次都需要在数组的第一个元素插入，每次插入数据的时间复杂度为 O(n), 需要插入n个数据， 所以时间复杂度为 O(n^2)


#### 选择排序

选择排序与插入排序类似，只是每次都从未排序区间找到最小(大)的元素，放到已排序区间的末尾。如图：

![](../../images/sort/select-sort.jpg)

时间复杂度与插入排序一样



- [桶排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/BucketSort.java)

- [堆排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/HeapSort.java)


- [归并排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/MergeSort.java)

- [快速排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/QuickSort.java)

- [希尔排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/ShellInsertSort.java)