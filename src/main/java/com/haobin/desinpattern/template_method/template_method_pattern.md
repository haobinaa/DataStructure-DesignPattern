### 一、模板方法模式概述

模版方法定义了一个算法的步骤，并且允许子类为一个或多个步骤提供实现。

一些流程，步骤都一样，每个步骤做的事情不一样，这就可也用到模板发方法


### 二、以公司上班为例

#### 1) 定义上班的流程
``` 
public abstract class Worker {

    protected String name;

    public Worker(String name) {
        this.name = name;
    }

    public final void workOneDay() {
        System.out.println("-----------------work start ---------------");
        enterCompany();
        computerOn();
        work();
        computerOff();
        exitCompany();
        System.out.println("-----------------work end ---------------");
    }

    /**
     * 工作
     */
    public abstract void work();

    /**
     * 关闭电脑
     */
    private void computerOff() {
        System.out.println(name + "关闭电脑");
    }

    /**
     * 打开电脑
     */
    private void computerOn() {
        System.out.println(name + "打开电脑");
    }

    /**
     * 进入公司
     */
    public void enterCompany() {
        System.out.println(name + "进入公司");
    }

    /**
     * 离开公司
     */
    public void exitCompany() {
        if (isNeedPrintDate()) {
            System.out.print(LocalDateTime.now() + "-->");
        }
        System.out.println(name + "离开公司");
    }

    /**
     * 打印时间hook
     * @return
     */
    public boolean isNeedPrintDate() {
        return false;
    }
}
```

#### 2) 具体实现
IT工作:
``` 
public class ITWorker extends Worker {

    public ITWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + "写程序-测bug-fix bug");
    }
}
```

HR工作:
``` 
public class HRWorker extends Worker {

    public HRWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + "看简历-打电话-接电话");
    }
}
```

### 3 UML类图
![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/desing-pattern/template_method.png)