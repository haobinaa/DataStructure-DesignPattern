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

#### 归并排序

归并排序的思想是将一个数组分成前后两部分，然后对前后两部分分别排序，再将排序好的结果归并到一起，这样整个数组就有序了。归并采用的是一种分治的思想(与递归类似)，将大问题分解成小问题来解决。

归并排序用递推公式表示如下:
``` 
递推公式：
merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))

终止条件：
p >= r 不用再继续分解
```

转换成伪代码应如下:
``` 
// 归并排序算法, A 是数组，n 表示数组大小
merge_sort(A, n) {
  merge_sort_c(A, 0, n-1)
}

// 递归调用函数
merge_sort_c(A, p, r) {
  // 递归终止条件
  if p >= r  then return

  // 取 p 到 r 之间的中间位置 q
  q = (p+r) / 2
  // 分治递归
  merge_sort_c(A, p, q)
  merge_sort_c(A, q+1, r)
  // 将 A[p...q] 和 A[q+1...r] 合并为 A[p...r]
  merge(A[p...r], A[p...q], A[q+1...r])
}
```
上述`merge(A[p...r], A[p...q], A[q+1...r])`的作用是将已经有序的A[p...q], A[q+1...r]合并成一个有序的数组并放入A[p..r]。


合并实现思路:

`merge`的实现首先申请一个临时数组tmp，与A[p..r]大小相同，将A[p..r]拷贝过去。用两个游标i,j分别指向A[p...q],A[q+1...r]的第一个元素，比较A[i]和A[j]，如果A[i]<=A[j]就把A[i]放入到临时数组tmp，并将i后移一位，否则将A[j]放入数组tmp，j后移一位。如果i或者j移动到区间最右边，当`i<q`时，代表左边未完全移入，则将左边剩余的全部移入数组。如果`i>q`则代表右边未完全移入，此时不必移动，因为右边的数据本来就是有序的了

将merge过程用代码实现:
``` 
public static void merge(int[] array, int low, int middle, int high) {
    // 申请一片空间拷贝原数组
    int[] tmp = new int[array.length];
    for (int i = 0; i <= high; i++) {
        tmp[i] = array[i];
    }
    // 记录左右游标位置
    int tmpLeft = low;
    int tmpRight = middle + 1;
    // 归并好的数组下标
    int current = low;
    // 比较左右两边数据，将小的放入并后移游标
    while (tmpLeft <= middle && tmpRight <= high) {
        if (tmp[tmpLeft] <= tmp[tmpRight]) {
            array[current] = tmp[tmpLeft];
            tmpLeft++;
        } else {
            array[current] = tmp[tmpRight];
            tmpRight++;
        }
        current++;
    }
    /**
     * 将数组左半剩余元素复制到目标数组中
     * 如果是右边有剩余，就不必操作(本身已经拷贝到之前数组了)
     */
    int remaining = middle - tmpLeft;
    for (int i = 0; i <= remaining; i++) {
        array[current + i] = tmp[tmpLeft + i];
    }
}
```

完整代码实现： [归并排序代码实现](../../src/main/java/com/haobin/datastructure/sort/MergeSort.java)

时间复杂度分析:
- 归并排序的时间复杂度很稳定，为 O(nlogn)

#### 快速排序

快排也是使用了分治的思想。思路是从数组下标 p 到 r 之间选择一个数据作为 pivot(基准点)，然后遍历 p 到 r 之间数据，将小于 pivot 的数据放在左边，大于 pivot 的数据放在右边，然后分别递归 pivot 左边
(p到q-1)和右边(q+1到r)，这样数据就有序了。如图：

![](../../images/sort/quick-sort.jpg)

将上述过程推导成递推公式如下:
``` 
递推公式：
quick_sort(p…r) = quick_sort(p…q-1) + quick_sort(q+1, r)

终止条件：
p >= r
```

快速排序的重点就是找到基准点(pivot)，这里用的思想是，用游标 i 把A[p...r]分成两部分， A[p..i-1]看成已处理的区间，A[i..r]是未处理的区间，每次从未处理区间取一个元素A[j]与 pivot 
对比，如果小于pivot 则将其加入已处理区间的尾部，也就是A[i]的位置。这样游标j遍历完未处理区间后，就完成了基准点的定位，即游标i所在位置。
 
 ![](../../images/sort/quick-sort-partition.jpg)
 
 找到基准点`partition`代码实现如下:
 ``` 
 public static int partition(int[] arr, int left, int right) {
     // 将基准点首先选为未处理的最后一个元素
     int pivot = arr[right];
     int i = left;
     for (int j = left; j <= right; j++) {
         if (arr[j] < pivot) {
             Util.swap(arr, i, j);
             i++;
         }
     }
     Util.swap(arr, i, right);
     return i;
 }
 ```
完整代码实现： [快速排序代码实现](../../src/main/java/com/haobin/datastructure/sort/QuickSort.java)



#### 其他排序的代码实现

- [桶排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/BucketSort.java)

- [堆排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/HeapSort.java)

- [快速排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/QuickSort.java)

- [希尔排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/ShellInsertSort.java)