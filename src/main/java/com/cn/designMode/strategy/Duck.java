package com.cn.designMode.strategy;

import com.cn.designMode.strategy.service.FlyBehavior;
import com.cn.designMode.strategy.service.QuackBehavior;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    public void fly() {
        flyBehavior.fly();
    }

    public void quack() {
        quackBehavior.quack();
    }

    public abstract void display();

}
