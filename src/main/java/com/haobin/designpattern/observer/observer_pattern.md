### 一、观察者模式概述

定义了对象之间的一对多的依赖，这样一来，当一个对象改变时，它的所有的依赖者都会收到通知并自动更新。

### 二、 以微信公众号为例子

#### 1) 定义主题接口
``` 
public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
```

#### 2) 定义观察者接口
``` 
public interface Observer  
{  
    public void update(String msg);  
} 
```

#### 3) 实现一个服务号
``` 
public class ObjectFor3D implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    private String msg;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    /**
     * 更新状态，通知所有观察者
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        this.notifyObservers();
    }
}
```

#### 4) 观察者
``` 
public class Observer1 implements Observer {

    private Subject subject;

    public Observer1(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println("observer1 得到 3D 号码  -->" + msg + ", 我要记下来。");
    }
}
```

#### 5) 通知
``` 
  //模拟一个3D的服务号  
  ObjectFor3D subjectFor3d = new ObjectFor3D();  
  //客户1  
  Observer observer1 = new Observer1(subjectFor3d);  
  Observer observer2 = new Observer2(subjectFor3d);  

  subjectFor3d.setMsg("20140420的3D号码是：127" );  
  subjectFor3d.setMsg("20140421的3D号码是：333" );  
```

### 三、 UML类图

![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/desing-pattern/observer_uml.png)

### 参考资料
- [设计模式 观察者模式 以微信公众号订阅为例](https://blog.csdn.net/lmj623565791/article/details/24179699)