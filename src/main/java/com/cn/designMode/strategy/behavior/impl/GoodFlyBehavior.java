package com.cn.designMode.strategy.behavior.impl;

import com.cn.designMode.strategy.behavior.FlyBehavior;

public class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞行技术高超。。。");
    }
}
