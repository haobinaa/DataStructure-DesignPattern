### 一、外观模式概述

外观模式就是提供一个统一的接口，用来访问子系统中的一群接口。

适配器模式用来解决接口不兼容问题，外观模式的目的在于如何简化接口，它可以将多个类的复杂的一切隐藏在背后，只显露出一个干净美观的外观


### 二、 外观模式(Facade)示例

回到家需要进行，开灯-> 开电视 -> 开空调一系列步骤， 睡觉前需要关灯-> 关电视 -> 关空调。
通过定义一个facade， 直接访问facade就可以进行这一系列操作了




### 三、 UML类图
![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/desing-pattern/facade.png)