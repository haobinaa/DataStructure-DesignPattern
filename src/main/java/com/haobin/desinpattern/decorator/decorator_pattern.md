### 一、装饰者模式概述
我们都知道，可以使用两种方式给一个类或者对象添加行为。

一是使用继承。继承是给一个类添加行为的比较有效的途径。通过使用继承，可以使得子类在拥有自身方法的同时，还可以拥有父类的方法。但是使用继承是静态的，在编译的时候就已经决定了子类的行为，我们不便于控制增加行为的方式和时机

 二是使用关联。组合即将一个对象嵌入到另一个对象中，由另一个对象来决定是否引用该对象来扩展自己的行为。这是一种动态的方式，我们可以在应用程序中动态的控制
 
 与继承相比，关联关系的优势就在于不会破坏类的封装性，且具有较好的松耦合性，可以使系统更加容易维护。但是它的缺点就在于要创建比继承更多的对象
 
 > 装饰者模式，动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更加有弹性的替代方案。当我们设计好了一个类，我们需要给这个类添加一些辅助的功能，并且不希望改变这个类的代码，这时候就是用上装饰者模式的时候了。
 
 ### 二、 以游戏装备为例，描述装饰者模式
 首先定义一个装备的接口,有攻击力和描述两个属性
 ``` 
 public interface IEquip {

     public int caculateAttack();

     public String description();
 }

 ```
 
具体一个装备，攻击力为5的戒指
``` 
public class RingEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战戒指";
    }
}
```

然后定义装饰器的接口
``` 
public interface IEquipDecorator extends IEquip {
}
```

实现一个具体的装饰器,有一个引用指向被装饰的装备，并增强装备的描述和攻击力方法
``` 
public class BlueGemDecorator implements IEquipDecorator {

    /**
     * 每个装饰品维护一个装备
     */
    private IEquip equip;

    public BlueGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int caculateAttack() {
        return 5 + equip.caculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + "+ 蓝宝石";
    }
}
```
### 三、 装饰器模式UML类图

![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/desing-pattern/decorator.png)


javaIO也是用的装饰器模式:
![](https://img-blog.csdn.net/20140421201934140?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbG1qNjIzNTY1Nzkx/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

### 参考资料
- [装饰者模式-带你重回传奇世界](https://blog.csdn.net/lmj623565791/article/details/24269409)
- [装饰者模式](http://www.cnblogs.com/chenssy/archive/2013/05/23/3094138.html)