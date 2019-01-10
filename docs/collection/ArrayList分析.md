### 1.概览
ArrayList的定义：
``` 
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```
默认大小为10: `private static final int DEFAULT_CAPACITY = 10;`

### 2.序列化
``` 
    transient Object[] elementData;
```
基于数组实现，保存元素的数组使用 transient 修饰，该关键字声明数组默认不会被序列化。ArrayList 具有动态扩容特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。ArrayList 重写了 writeObject() 和 readObject() 来控制只序列化数组中有元素填充那部分内容
  
writeObject() :
``` 
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException{
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
```

### 3.扩容
添加元素时使用 `ensureCapacityInternal() `方法来保证容量足够，如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 `oldCapacity + (oldCapacity >> 1)`，也就是旧容量的 1.5 倍。

扩容操作需要调用 `Arrays.copyOf() `把原数组整个复制到新数组中，因此最好在创建 ArrayList 对象时就指定大概的容量大小，减少扩容操作的次数。
``` 
  public boolean add(E e) {
      ensureCapacityInternal(size + 1);  
      elementData[size++] = e;
      return true;
  }
  
  private void ensureCapacityInternal(int minCapacity) {
      // 如果数组为空则返回最大容量
      if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
          minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
      }

      ensureExplicitCapacity(minCapacity);
  }
  
  private void ensureExplicitCapacity(int minCapacity) {
      // 修改次数+1
      modCount++;
      // 如果超出现有长度，则进行扩容
      if (minCapacity - elementData.length > 0)
          grow(minCapacity);
  }
  
  private void grow(int minCapacity) {
      int oldCapacity = elementData.length;
      // 扩容为原容量的1.5倍
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      // 如果扩容后容量满足，则就为扩容后的容量
      if (newCapacity - minCapacity < 0)
          newCapacity = minCapacity;
      // 如果扩容后超出数组最大值则抛出异常
      if (newCapacity - MAX_ARRAY_SIZE > 0)
          newCapacity = hugeCapacity(minCapacity);
      elementData = Arrays.copyOf(elementData, newCapacity);
  }
  
  // 判断扩容后容量是否正常(大于0并且小于数组最大值)
  private static int hugeCapacity(int minCapacity) {
      if (minCapacity < 0) // overflow
          throw new OutOfMemoryError();
      return (minCapacity > MAX_ARRAY_SIZE) ?
          Integer.MAX_VALUE :
          MAX_ARRAY_SIZE;
  }
```

### 4.删除元素
``` 
public E remove(int index) {
    rangeCheck(index);

    modCount++;
    E oldValue = elementData(index);
    // 将被删除元素之后的内容都前移，覆盖被删除元素
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; 

    return oldValue;
}
```

### 5.结构改变(fail-fast机制)
modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代等操作时，需要比较操作前后 modCount 是否改变，如果改变了需要抛出 ConcurrentModificationException。

在ArrayList迭代的时候如果对其结构进行修改就会抛出ConcurrenModificationException

Fail_Fast机制原理：在ArrayList在调用add(),remove(),clear(),ensureCapacity()
这些会修改数据结构的方法中都会使modCount++。在获取迭代器（itr.next方法）的时候会把modCount赋值给迭代器的expectedModCount变量。此时modCount与expectedModCount
肯定相等，在迭代元素的过程中如果ArrayList调用自身方法使集合发生变化，那么modCount肯定会变，此时modCount与expectedModCount肯定会不相等。就会抛出异常


如下流程:
在Iterator迭代过程中，next方法:
``` 
public E next() {
    checkForComodification();
 try {
    E next = get(cursor);
    lastRet = cursor++;
    return next;
 } catch (IndexOutOfBoundsException e) {
    checkForComodification();
    throw new NoSuchElementException();
 }
}
```
首先在next()方法中会调用checkForComodification()方法，然后根据cursor的值获取到元素，接着将cursor的值赋给lastRet，并对cursor的值进行加1操作。初始时，cursor为0，lastRet为-1，那么调用一次之后，cursor的值为1，lastRet的值为0。注意此时，modCount为0，expectedModCount也为0。

chekForCommodificatio如下:
``` 
final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
```

假如我们调用remove方法，如上一小节所示，会对`modCount`加1，但是expectModCount这时候为0， 
当下一次调用`chekForCommodification`的时候，就会抛出`ConcurrentModificationException`异常

#### 多线程环境下
在单线程情况下， 只需要调用Iterator提供的修改Collection结构的方法就好了，不要调用List自身的。比如下面代码会抛出ConcurrentModificationException异常
``` 
public class Test {
    public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
                list.remove(integer);
        }
    }
}
```
改为itearator的remove则不会出问题:
``` 
public class Test {
    public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
                iterator.remove();
        }
    }
}
```

但是这个程序在多线程下就不能保证了，比如，稍微改造一下:
``` 
public class Test {
    static ArrayList<Integer> list = new ArrayList<Integer>();
    public static void main(String[] args)  {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Thread thread1 = new Thread() {
            public void run() {
                Iterator<Integer> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Integer integer = iterator.next();
                    System.out.println(integer);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        };
        Thread thread2 = new Thread() {
            public void run() {
                Iterator<Integer> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Integer integer = iterator.next();
                    if (integer == 2)
                        iterator.remove(); 
                }
            };
        };
        thread1.start();
        thread2.start();
    }
}
```
初始时，线程1和线程2中的modCount、expectedModCount都为0，

当线程2通过iterator.remove()删除元素时，会修改modCount值为1，并且会修改线程2中的expectedModCount的值为1，

而此时线程1中的expectedModCount值为0，虽然modCount不是volatile变量，不保证线程1一定看得到线程2修改后的modCount的值，但是也有可能看得到线程2对modCount的修改，这样就有可能导致线程1中比较expectedModCount和modCount不等，从而抛出异常。

因此一般有2种解决办法：
1. 在使用iterator迭代的时候使用synchronized或者Lock进行同步；
2. 使用并发容器CopyOnWriteArrayList代替ArrayList和Vector。

##### 隐藏的迭代器
容器含有一些隐藏的迭代器，hashCode，equal，containsAll，removeAll和retainAIl等方法，都会对容器进行迭代。
这种隐式的迭代器， 在多线程的环境下会出现问题，抛出异常， 所以尽量用并发容器，不要手动去同步方法


### 6.参考资料
- [Java ConcurrentModificationException异常原因和解决方法](http://www.cnblogs.com/dolphin0520/p/3933551.html)
- [ArrayList源码分析](https://blog.csdn.net/u010887744/article/details/49496093)
- [迭代器与ConcurrentModificationException](https://www.jianshu.com/p/134ee9cfa65b)