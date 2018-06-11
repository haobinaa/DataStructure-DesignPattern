### 1.概述
HashSet 是一个不允许存储重复元素的集合，它的实现比较简单


### 2.成员变量
``` 
// 存放最终的数据
    private transient HashMap<E,Object> map;
// 所有写入Map的Value
    private static final Object PRESENT = new Object();
```


### 3.构造函数
``` 
public HashSet() {
    map = new HashMap<>();
}

public HashSet(int initialCapacity, float loadFactor) {
    map = new HashMap<>(initialCapacity, loadFactor);
}  
```

### 4.add
``` 
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }
```
比较关键的就是这个` add() `方法。 可以看出它是将存放的对象当做了 `HashMap `的健，`value `都是相同的` PRESENT `。由于` HashMap `的 `key` 是不能重复的，所以每当有重复的值写入到 `HashSet` 时，`value` 会被覆盖，但 `key` 不会收到影响，这样就保证了 `HashSet `中只能存放不重复的元素。