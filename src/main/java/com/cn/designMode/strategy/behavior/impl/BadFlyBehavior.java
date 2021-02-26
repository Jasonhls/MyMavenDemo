package com.cn.designMode.strategy.behavior.impl;

import com.cn.designMode.strategy.behavior.FlyBehavior;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 10:44
 **/
public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞行技术一般。。。");
    }
}
