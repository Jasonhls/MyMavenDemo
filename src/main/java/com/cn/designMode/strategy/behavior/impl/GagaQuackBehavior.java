package com.cn.designMode.strategy.behavior.impl;

import com.cn.designMode.strategy.behavior.QuackBehavior;

public class GagaQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
}
