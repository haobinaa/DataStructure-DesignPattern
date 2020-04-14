package com.haobin.designpattern.build.builder;

/**
 * @Author HaoBin
 * @Create 2020/4/14 15:34
 * @Description: 建造者模式创建对象
 **/
public class User {
    // 一堆属性
    private String name;
    private String password;
    private String nickName;
    private int age;

    // 同样私有化构造函数
    private User(String name, String password, String nickName, int age) {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
    }



     static class UserBuilder {
        // 和 user 一样的属性
         private String  name;
         private String password;
         private String nickName;
         private int age;

         private UserBuilder() {}

         public UserBuilder name(String name) {
             this.name = name;
             return this;
         }
         // .. 一堆设置属性的方法


         // build 方法将 UserBuild 中的属性赋值给 User
         // 并且可以做一些校验
         public User build() {
             if (name == null || password == null) {
                 throw new RuntimeException("用户名和密码必填");
             }
             if (age <= 0 || age >= 150) {
                 throw new RuntimeException("年龄不合法");
             }
             // 还可以做赋予”默认值“的功能
             if (nickName == null) {
                 nickName = name;
             }
             return new User(name, password, nickName, age);
         }
    }
}
