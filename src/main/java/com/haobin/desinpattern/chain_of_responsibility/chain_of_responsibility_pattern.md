### 一、责任链模式概述

让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。

职责链上的处理者负责处理请求，客户只需要将请求发送到职责链上即可，无须关心请求的处理细节和请求的传递，所以职责链将请求的发送者和请求的处理者解耦了，这就是职责链的设计动机。

### 二、以请假为例子

请假的时候，如果三天以下，需要辅导员签字，三到七天需要系主任签字....请求一级一级的往上传递

#### 1) 定义请假对象
``` 
public class LeaveNode {
    /** 请假天数 **/
    private  int number;

    /** 请假人 **/
    private String person;
 // 省略 getter setter.......
}
```

#### 2) 抽象处理者
``` 
public abstract class Leader {
    /** 姓名 **/
    public String name;

    /** 后继者 **/
    protected Leader successor;

    public Leader(String name){
        this.name = name;
    }

    public void setSuccessor(Leader successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(LeaveNode LeaveNode);
}
```

#### 3) 具体处理者

辅导员(Instructor)处理三天以下的请假:
``` 
public class Instructor extends Leader {

    public Instructor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if (leaveNode.getNumber() < 3) {
            System.out.println("辅导员" + name + "审批" +leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {
            this.successor.handleRequest(leaveNode);
        }
    }
}
```

系主任(DepartmentHead)处理三到七天的请假:
``` 
public class DepartmentHead extends Leader {

    public DepartmentHead(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if (leaveNode.getNumber() <= 7) {
            System.out.println(
                    "系主任" + name + "审批" + leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {     //否则传递给院长
            if (this.successor != null) {
                this.successor.handleRequest(leaveNode);
            }
        }
    }
}
```

院长处理七到十天的请假:
``` 
public class Dean extends Leader {

    public Dean(String name) {
        super(name);
    }

    public void handleRequest(LeaveNode leaveNode) {
        if (leaveNode.getNumber() <= 10) {
            System.out.println(
                    "院长" + name + "审批" + leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {
            if (this.successor != null) {
                this.successor.handleRequest(leaveNode);
            }
        }
    }
}
```

#### 4) 具体请假流程
``` 
        Leader instructor = new Instructor("毕玄");
        Leader department = new DepartmentHead("多隆");
        Leader dean = new Dean("风清扬");
        // 设置处理链顺序
        instructor.setSuccessor(department);
        department.setSuccessor(dean);

        LeaveNode leaveNode1 = new LeaveNode(3, "张三");
        instructor.handleRequest(leaveNode1);

        LeaveNode leaveNode2 = new LeaveNode(5, "李四");
        instructor.handleRequest(leaveNode2);

        LeaveNode leaveNode3 = new LeaveNode(9, "王五");
        instructor.handleRequest(leaveNode3);
```

### 3 责任链UML类图
### 参考资料
- [职责链模式](http://www.cnblogs.com/chenssy/p/3332193.html)