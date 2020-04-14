package com.haobin.designpattern.structure.proxy;

/**
 * @Author HaoBin
 * @Create 2020/4/14 15:51
 * @Description: 飞天机器人
 **/
public class FlyRobot implements RobotService {


    @Override
    public String work() {
        return "fly";
    }
}
