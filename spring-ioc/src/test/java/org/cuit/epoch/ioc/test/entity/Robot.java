package org.cuit.epoch.ioc.test.entity;

public class Robot {
    //需要注入 hand 和 mouth

    private String name;
    private Hand hand;
    private Mouth mouth;

    public Robot() {
    }

    public Robot(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("robot's name = " + name);
        hand.waveHand();
        mouth.speak();
    }
}