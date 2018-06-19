### 一、适配器模式概述

适配器模式就是将一个类的接口，转换成客户期望的另一个接口。适配器让原本接口不兼容的类可以合作无间。


### 二、适配器模式示例

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
public class Dog {

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
public class DogAdapter implements Robot {

    Dog dog;

    public DogAdapter(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void cry() {
        System.out.println("模拟狗叫：");
        dog.wang();
    }

    @Override
    public void move() {
        System.out.println("模拟狗移动:");
        dog.run();
    }
}
```

同理，还可以定义其他适配器，让机器人去适配更多的动物
### 三、UML类图


### 参考资料

- [设计模式读书笔记](http://www.cnblogs.com/chenssy/p/3204504.html)