package com.cn.designMode.strategy.service;

public class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("good fly");
    }
}
