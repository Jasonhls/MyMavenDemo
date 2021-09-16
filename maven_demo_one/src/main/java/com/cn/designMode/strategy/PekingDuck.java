package com.cn.designMode.strategy;

import com.cn.designMode.strategy.behavior.impl.NoFlyBehavior;
import com.cn.designMode.strategy.behavior.impl.NoQuackBehavior;

public class PekingDuck extends Duck {

    public PekingDuck() {
        flyBehavior = new NoFlyBehavior();
        quackBehavior = new NoQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("我是北京烤鸭");
    }
}
