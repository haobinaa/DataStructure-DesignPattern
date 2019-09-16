### 一、适配器模式概述

适配器模式就是将一个类的接口，转换成客户期望的另一个接口。适配器让原本接口不兼容的类可以合作无间。

#### 默认适配器模式
 Appache commons-io 包中的 `FileAlterationListener`接口定义了很多方法 ， 用来对文件或文件夹做监控
 ``` 
 public interface FileAlterationListener {
     void onStart(final FileAlterationObserver observer);
     void onDirectoryCreate(final File directory);
     void onDirectoryChange(final File directory);
     void onDirectoryDelete(final File directory);
     void onFileCreate(final File file);
     void onFileChange(final File file);
     void onFileDelete(final File file);
     void onStop(final FileAlterationObserver observer);
 }
 ```
 
 此接口的一大问题是抽象方法太多了，如果我们要用这个接口，意味着我们要实现每一个抽象方法，如果我们只是想要监控文件夹中的文件创建和文件删除事件，可是我们还是不得不实现所有的方法，很明显，这不是我们想要的。
 
 所以，我们需要下面的一个适配器，它用于实现上面的接口，但是所有的方法都是空方法，这样，我们就可以转而定义自己的类来继承下面这个类即可。
 
 ``` 
 public class FileAlterationListenerAdaptor implements FileAlterationListener {
 
     public void onStart(final FileAlterationObserver observer) {}
 
     public void onDirectoryCreate(final File directory) {}
 
     public void onDirectoryChange(final File directory) {}
 
     public void onDirectoryDelete(final File directory) {}
 
     public void onFileCreate(final File file) {}
 
     public void onFileChange(final File file) {}
 
     public void onFileDelete(final File file) {}
 
     public void onStop(final FileAlterationObserver observer) {}
 }
 ```

现在我们可以只实现我们想实现的方法了:
``` 
public class FileMonitor extends FileAlterationListenerAdaptor {
    public void onFileCreate(final File file) {
        // 文件创建
        doSomething();
    }

    public void onFileDelete(final File file) {
        // 文件删除
        doSomething();
    }
}
```
#### 对象适配器模式

通过一个让机器人模仿其他动物的例子来阐述适配器模式

首先定义一个机器人接口
``` 
public interface Robot {

    void cry();

    void move();
}

```

机器人的默认实现， 可以cry() 和 move():
``` 
public class ImitateRobot implements Robot {

    @Override
    public void cry() {
        System.out.println("机器人叫，乌拉乌拉");
    }

    @Override
    public void move() {
        System.out.println("机器人移动，噜噜噜噜");
    }
}
```
现在有一个Dog, 他有wang() 和 run()两个功能：
``` 
public interface Dog {

    public void wang() {
        System.out.println("汪汪汪");
    }

    public void run() {
        System.out.println("奔跑的狗...........");
    }
}
```
实现一个适配器，来让机器人可以用Dog的方法
``` 
public class RobotAdapter implements Dog {

    Robot robot;

    public RobotAdapter(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void wang() {
        System.out.println("模拟狗叫：");
        robot.cry();
    }

    @Override
    public void run() {
        System.out.println("模拟狗移动:");
        robot.move();
    }
}
```

同理，还可以定义其他适配器，让机器人去适配更多的动物。

对象适配模式本质上是， 我们需要机器人会模仿狗， 这个时候需要定义一个适配器， 让适配器来充当狗， 但是适配器里面的方法还是机器人的方法

UML类图如下：

![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/desing-pattern/adapter.png)

#### 类适配模式
目标角色的接口代码如下:
``` 
public interface Target {
    /**
     * 这是源类Adaptee中也有的方法
     */

    public void sampleOperation1();
    /**
     * 这是源类Adaptee中没有的方法
     */
    public void sampleOperation2();
}

```

需要适配的角色是一个具体类， 他只有`sampleOperation1()`：
``` 
public class Adaptee {
    public void sampleOperation1() {
       System.out.println("Operation 1st");
    }
}
```

适配角色扩展了Adaptee， 同时又实现了目标角色的Target的接口
``` 
public class Adapter extends Adaptee implements Target {
    @Override
    public void sampleOperation2() {
       System.out.println("Operation 2nd");
    }
}
```

### 总结

### 参考资料

- [设计模式读书笔记](http://www.cnblogs.com/chenssy/p/3204504.html)