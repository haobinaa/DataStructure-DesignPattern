### 一、单例模式概述
单例模式就是全局公用一个实例


### 二、书写单例模式
一个引用指向全局实例， 私有化构造函数防止外部实例化， 提供一个静态方法获得实例引用
#### 1) 懒汉式，线程不安全
```
public class SingleTon {

    public static SingleTon instance;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (instance == null) {
            instance = new SingleTon();
        }
        return instance;
    }
} 
```
这种方式在多线程环境下会出现产生多个实例的现象

#### 2) 懒汉式，线程安全
``` 
    public static synchronized SingleTon getInstance() {
        if (instance == null) {
            instance = new SingleTon();
        }
        return instance;
    }
```
对`getInstance`方法加锁，保证了线程安全，但是降低了效率，因为任何时候都只有一个线程能调用`getInstance`方法，但是同步操作只有第一次调用的时候才需要

#### 3）双重检测(DCL)
``` 
    public static synchronized SingleTon getInstance() {
        if (instance == null) {                         //Single Checked
            synchronized (SingleTon.class) {
                if (instance == null) {                 //Double Checked
                    instance = new SingleTon();
                }
            }
        }
        return instance ;
    }
```

这段代码看起来很完美，很可惜，它是有问题。主要在于instance = new Singleton()这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情:
- 1.给 instance 分配内存
- 2.调用 Singleton 的构造函数来初始化成员变量
- 3.将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）

但是在 JVM 的即时编译器中存在指令重排序的优化。也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是 1-2-3 也可能是 1-3-2。如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，这时 instance 已经是非 null 了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。

所以我们需要将`instance`申明成volatile
``` 
public class Singleton {
    private volatile static Singleton instance; //声明成 volatile
    private Singleton (){}
    public static Singleton getSingleton() {
        if (instance == null) {                         
            synchronized (Singleton.class) {
                if (instance == null) {       
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
   
}
```

#### 4) static final field方式
以上创建单例的方法包含了很多隐藏的问题，增加了复杂性，如果单例的实例被声明成 static 和 final 变量了，在第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的。

``` 
public class Singleton{
    //类加载时就初始化
    private static final Singleton instance = new Singleton();
    
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}
```
这样就不会有双重锁检测的问题了，但是缺点是加载类一开始就初始化了，还没等待客户端的调用，如果单例的创建是依赖某些参数，那么这些参数必须一开始就传给他，无法使用懒加载

#### 5) 静态内部类
这种方法是《Effective Java》上所推荐的
``` 
public class Singleton {  
    private static class SingletonHolder {  
        private static final Singleton INSTANCE = new Singleton();  
    }  
    private Singleton (){}  
    public static final Singleton getInstance() {  
        return SingletonHolder.INSTANCE; 
    }  
}
```

这种写法仍然使用JVM本身机制保证了线程安全问题；由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷。
### 参考资料

- [如何正确的写出单例模式](http://wuchong.me/blog/2014/08/28/how-to-correctly-write-singleton-pattern/)