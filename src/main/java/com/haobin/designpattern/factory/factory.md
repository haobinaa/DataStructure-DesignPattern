### 一、工厂模式概述

- 简单工厂模式：据传递的参数不同，返回不同类的实例
- 工厂方法模式：定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法模式让实例化推迟到子类。
- 抽象工厂模式：提供一个接口，用于创建相关或者依赖对象的家族，而不需要明确指定具体类

### 二、通过服装工厂来描述工厂模式

#### 1) 简单工厂(又叫静态工厂)模式
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
定义简单的工厂，通过一个静态方法来生产这两种服装
``` 
public class SimpleClothesFactory {

    public static Clothes makeClothes(String type) {
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

#### 2) 工厂方法模式
现在每个地方都有自己的工厂来生产服装，比如浙江要生产自己的服装，首先定义一个产品浙江棉毛裤

``` 
public class ZJMMClothes extends Clothes {

    public ZJMMClothes() {
        this.name = "浙江棉毛裤";
    }
}
```

抽象一个服装工厂作为总厂, 将出售服装和制作服装的方法抽离出来
``` 
public abstract class ClothesFactory {

    public Clothes sellClothes(String type) {
        Clothes clothes = null;
        clothes = makeClothes(type);
        clothes.colorClothes();
        clothes.cutClothes();
        return clothes;
    }

    abstract Clothes makeClothes(String type);

}
```

定义浙江服装裤工厂
``` 
public class ZJClothesFactory extends ClothesFactory {

    @Override
    Clothes makeClothes(String type) {
        Clothes clothes = null;
        if (type.equals("棉毛裤")) {
            clothes = new ZJMMClothes();
        }
        return clothes;
    }
}
```

可以看到，工厂方法模式将实例化对象交给了每个工厂，这样就可以更多种类的对象

#### 3) 抽象工厂模式
有两间塑料加工厂（A厂仅生产容器类产品；B厂仅生产模具类产品）；随着客户需求的变化，A厂所在地的客户需要也模具类产品，B厂所在地的客户也需要容器类产品

定义抽象工厂，可以生产容器类和模具类两种产品
``` 
public abstract class Factory {
    public abstract AbstractProduct ManufactureContainer();
    public abstract AbstractProduct ManufactureMould();
}
```

定义产品和容器、模具产品的抽象类
``` 
public abstract class AbstractProduct {
    public abstract void Show();
}

public abstract class ContainerProduct extends AbstractProduct {

    @Override
    public abstract void Show();
}

public abstract class MouldProduct extends AbstractProduct {

    @Override
    public abstract void Show();
}
```

具体产品的创建
``` 
class ContainerProductA extends ContainerProduct {

    @Override
    public void Show() {
        System.out.println("生产容器产品A");
    }
}

class ContainerProductB extends ContainerProduct {

    @Override
    public void Show() {
        System.out.println("生产容器产品B");
    }
}

class MouldProductA extends MouldProduct {

    @Override
    public void Show() {
        System.out.println("生产模具产品A");
    }
}

class MouldProductB extends MouldProduct {

    @Override
    public void Show() {
        System.out.println("生产模具产品B");
    }
}
```

工厂A可以产生容器A和模具A，工厂B可以生产模具B和容器B了