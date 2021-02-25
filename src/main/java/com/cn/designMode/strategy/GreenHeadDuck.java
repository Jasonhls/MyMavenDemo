package com.cn.designMode.strategy;

import com.cn.designMode.strategy.service.GagaQuackBehavior;
import com.cn.designMode.strategy.service.GoodFlyBehavior;

public class GreenHeadDuck extends Duck {

    public GreenHeadDuck() {
        flyBehavior = new GoodFlyBehavior();
        quackBehavior = new GagaQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("display green");
    }
}
