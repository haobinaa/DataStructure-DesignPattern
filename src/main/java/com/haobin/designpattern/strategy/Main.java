/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.strategy;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Main.java, v0.1 2018/6/13 9:14 HaoBin 
 */
public class Main {

    public static void main(String[] args) {
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
    }
}