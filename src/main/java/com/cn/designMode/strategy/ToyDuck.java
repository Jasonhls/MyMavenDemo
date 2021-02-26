package com.cn.designMode.strategy;

import com.cn.designMode.strategy.behavior.impl.BadFlyBehavior;
import com.cn.designMode.strategy.behavior.impl.GegeQuackBehavior;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 10:49
 **/
public class ToyDuck extends Duck{

    public ToyDuck() {
        flyBehavior = new BadFlyBehavior();
        quackBehavior = new GegeQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("我是toy鸭子");
    }
}
