/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.adapter;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Main.java, v0.1 2018/6/19 10:27 HaoBin 
 */
public class Main {

    public static void main(String[] args) {
        ImitateRobot imitateRobot = new ImitateRobot();
        Dog dog = new Dog();
        Bird bird = new Bird();

        Robot imitateDog = new DogAdapter(dog);
        imitateDog.cry();
        imitateDog.move();

        Robot imitateBird = new BirdAdapter(bird);
        imitateBird.cry();
        imitateBird.move();
    }
}