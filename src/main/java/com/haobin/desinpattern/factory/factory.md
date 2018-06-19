### 一、工厂模式概述
工厂方法模式定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个

### 二、通过服装工厂来描述工厂模式

#### 1) 简单工厂模式
首先定义服装抽象类:
``` 
public abstract class Clothes {

    protected String name;

    public void colorClothes() {
        System.out.println("给衣服上色");
    }

    public void cutClothes() {
        System.out.println("裁剪衣服");;
    }
}
```

现在需要卖出两种服装，牛仔裤和棉毛裤
``` 
public class NZClothes extends Clothes {

    public NZClothes() {
        this.name = "牛仔裤";
    }
}

public class MMCLothes extends Clothes {

    public MMCLothes() {
        this.name = "棉毛裤";
    }
}
```
定义简单的工厂，来生产这两种服装
``` 
public class SimpleClothesFactory {

    public Clothes makeClothes(String type) {
        Clothes clothes = null;
        if (type.equals("牛仔裤")) {
            clothes = new NZClothes();
        } else if (type.equals("棉毛裤")) {
            clothes = new MMCLothes();
        }
        return clothes;
    }
}
```