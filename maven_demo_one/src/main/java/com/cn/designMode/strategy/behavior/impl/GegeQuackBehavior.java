package com.cn.designMode.strategy.behavior.impl;

import com.cn.designMode.strategy.behavior.QuackBehavior;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 10:45
 **/
public class GegeQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("咯咯叫");
    }
}
