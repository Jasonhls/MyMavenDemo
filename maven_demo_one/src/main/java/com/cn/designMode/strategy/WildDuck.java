package com.cn.designMode.strategy;

import com.cn.designMode.strategy.behavior.impl.GagaQuackBehavior;
import com.cn.designMode.strategy.behavior.impl.GoodFlyBehavior;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 09:14
 **/
public class WildDuck extends Duck{

    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
        quackBehavior = new GagaQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("我是野鸭子");
    }
}
