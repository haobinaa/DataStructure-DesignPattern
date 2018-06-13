### 一、设计原则

#### 1） 单一职责原则
一个类，只有一个引起它变化的原因

#### 2） 开闭原则
开闭原则就是说对扩展开放，对修改关闭。程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。所以一句话概括就是：为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类

#### 3）里氏代换原则
任何基类可以出现的地方，子类一定可以出现。只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。里氏代换原则是对“开-闭”原则的补充。

#### 4) 依赖倒转原则
依赖于抽象，不要依赖于具体。简单的说就是要求对抽象进行编程，不要对实现进行编程，这样就降低了客户与实现模块间的耦合

#### 5) 接口隔离原则
使用多个隔离的接口，比使用单个接口要好。

#### 6) 合成复用原则
  合成复用原则就是指在一个新的对象里通过关联关系（包括组合关系和聚合关系）来使用一些已有的对象，使之成为新对象的一部分；新对象通过委派调用已有对象的方法达到复用其已有功能的目的。简言之：要尽量使用组合/聚合关系，少用继承。
  
#### 7) 最少知道原则
一个实体应当尽量少的与其他实体之间发生相互作用，使得系统功能模块相对独立。

### 二、类关系
类关系涉及依赖、关联、聚合、组合和泛化这五种关系，耦合度依次递增。关于耦合度，可以简单地理解为当一个类发生变更时，对其他类造成的影响程度，影响越小则耦合度越弱，影响越大耦合度越强。

#### 1） 依赖

依赖关系用虚线加箭头表示, 如student 依赖 computer：

![](https://img-blog.csdn.net/20170302193322250?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSzM0NkszNDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

类A要完成某个功能引用了类B，则类A依赖类B。依赖在代码中主要体现为 **类A的某个成员方法的返回值、形参、局部变量或静态方法的调用B** ，则表示类A引用了类B。

> 一般而言，依赖关系在Java中体现为局域变量、方法的形参，或者对静态方法的调用。

#### 2） 关联
关联关系使用实线加箭头表示，类之间的关系比依赖要强。 如student 关联 teacher：

![](https://img-blog.csdn.net/20170302194329814?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSzM0NkszNDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

发生关联关系的两个类，**类A成为类B的属性**，而属性是一种更为紧密的耦合，更为长久的持有关系。 

关联关系有单向关联、双向关联、自身关联、多维关联等等。其中后三个可以不加箭头。

> 在Java中，关联关系一般使用成员变量来实现。 

#### 3） 聚合

聚合关系使用实线加空心菱形表示。聚合用来表示集体与个体之间的关联关系。 如classes 和 student之间存在聚合关系

![](https://img-blog.csdn.net/20170302212119790?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSzM0NkszNDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

聚合是关联关系的一种特例，他体现的是整体与部分、拥有的关系，即has-a的关系。在代码层面，聚合和关联关系是一致的，只能从语义级别来区分。普通的关联关系中，a类和b类没有必然的联系，而聚合中，需要b类是a类的一部分，是一种”has-a“的关系，即 a has-a b; 比如家庭有孩子，屋子里有空调。

但是，has 不是 must has，a可以有b，也可以没有。a是整体，b是部分，整体与部分之间是可分离的，他们可以具有各自的生命周期，部分可以属于多个整体对象，也可以为多个整体对象共享。

> 不同于关联关系的平等地位，聚合关系中两个类的地位是不平等。

#### 4) 组合

复合关系使用实线加实心菱形表示。组合又叫复合，用来表示个体与组成部分之间的关联关系。 如 student 和 heart 之间存在组合关系:

![](https://img-blog.csdn.net/20170302212251526?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSzM0NkszNDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

组合也是关联关系的一种特例，他体现的是一种contains-a的关系，这种关系比聚合更强，也称为强聚合。

组合同样体现整体与部分间的关系，但此时整体与部分是不可分的，整体的生命周期结束也就意味着部分的生命周期结束。

只看代码，你是无法区分关联，聚合和组合的，具体是哪一种关系，只能从语义级别来区分。

> 同样，组合关系中，两个类的关系也是不平等的。

#### 5） 泛化
泛化是学术名称，通俗的来讲，泛化指的是类与类之间的继承关系和类与接口之间的实现关系。

继承关系使用直线加空心三角形表示。类图结构如下：

！[](https://img-blog.csdn.net/20170302215508836?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSzM0NkszNDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

类接口的实现关系使用虚线加空心三角形表示。类图结构如下：

![](https://img-blog.csdn.net/20170302215900310?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSzM0NkszNDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 

### 参考资料
- [重新认识java](https://blog.csdn.net/qq_31655965/article/details/54645220)
- [认识UML类关系](https://blog.csdn.net/K346K346/article/details/59582926)