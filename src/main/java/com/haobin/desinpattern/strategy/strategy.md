### 一、策略模式概述
定义了算法族，分别封装起来，让它们之间可相互替换，此模式让算法的变化独立于使用算法的客户。

### 二、以游戏角色为背景

`Head First设计模式` 上以出行方式为例子，我觉得鸿洋博客中游戏角色为例子更加生动

每个角色对应一个名字，每类角色对应一种样子(display)，每个角色拥有一个逃跑(run)、攻击(attack)、防御(defend)的技能。

遵循设计的原则，找出应用中可能需要变化的部分，把它们独立出来，不要和那些不需要变化的代码混在一起。我们发现，对于每个角色的display，attack，defend，run
都是有可能变化的，于是我们必须把这写独立出来。再根据另一个设计原则：针对接口（超类型）编程，而不是针对实现编程。我们把变化的部分都抽象成接口:
``` 
public interface IAttackBehavior  
{  
    void attack();  
} 

public interface IDefendBehavior  
{  
    void defend();  
}  

public interface IDisplayBehavior  
{  
    void display();  
}  

public interface IRunBehavior {
    void run();
}
```

然后分别对每个部分写策略实现(实现变化部分)
``` 
public class DefendTBS implements IDefendBehavior {

    @Override
    public void defend() {
        System.out.println("铁布衫");
    }
}

public class AttackJY implements IAttackBeHavior 

// 省略
........
```


基类Role设计如下:
``` 
public abstract class Role {

    protected String name;

    protected IDefendBehavior defendBehavior;
    protected IDisplayBehavior displayBehavior;
    protected IRunBehavior runBehavior;
    protected IAttackBeHavior attackBehavior;

    public Role setDefendBehavior(IDefendBehavior defendBehavior)
    {
        this.defendBehavior = defendBehavior;
        return this;
    }

    public Role setDisplayBehavior(IDisplayBehavior displayBehavior)
    {
        this.displayBehavior = displayBehavior;
        return this;
    }

    public Role setRunBehavior(IRunBehavior runBehavior)
    {
        this.runBehavior = runBehavior;
        return this;
    }

    public Role setAttackBehavior(IAttackBeHavior attackBehavior)
    {
        this.attackBehavior = attackBehavior;
        return this;
    }

    protected void display()
    {
        displayBehavior.display();
    }

    protected void run()
    {
        runBehavior.run();
    }

    protected void attack()
    {
        attackBehavior.attack();
    }

    protected void defend()
    {
        defendBehavior.defend();
    }
}
```

这样我们的角色模板只需要传入一个name即可:
``` 
public class RoleImpl extends Role {

    public RoleImpl(String name) {
        this.name = name;
    }
}
```

现在我们需要一个金蝉脱壳，降龙十八掌！，铁布衫，样子1的角色A只需要这样：

``` 
  Role roleA = new RoleImpl("A");
  roleA.setAttackBehavior(new AttackJY())//
          .setDefendBehavior(new DefendTBS())//
          .setDisplayBehavior(new DisplayA())//
          .setRunBehavior(new RunJCTQ());
  System.out.println(roleA.name + ":");
  roleA.run();
  roleA.attack();
  roleA.defend();
  roleA.display();
```
我们可以根据策略的实现，组合出不同的角色

### 3. UML类图
### 4. 参考资料
- [HeadFirst 设计模式]
- [设计模式 策略模式 以角色游戏为背景](https://blog.csdn.net/lmj623565791/article/details/24116745)
