package com.haobin.designpattern.structure.proxy;

/**
 * @Author HaoBin
 * @Create 2020/4/14 15:51
 * @Description: 飞天机器人的代理
 **/
public class FlyRobotProxy implements RobotService{

    // 内部持有目标类
    private RobotService flyRobot;

    public FlyRobotProxy(RobotService flyRobot) {
        this.flyRobot = flyRobot;
    }

    @Override
    public String work() {
        // 在执行目标方法前，执行一些增强的内容
        System.out.println("给飞天机器人加燃料");
        return flyRobot.work();
    }
}
