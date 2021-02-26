package com.cn.designMode.strategy.behavior.impl;

import com.cn.designMode.strategy.behavior.FlyBehavior;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 10:45
 **/
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞行");
    }
}
