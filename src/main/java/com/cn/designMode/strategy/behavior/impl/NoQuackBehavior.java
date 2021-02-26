package com.cn.designMode.strategy.behavior.impl;

import com.cn.designMode.strategy.behavior.QuackBehavior;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 10:46
 **/
public class NoQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不能叫");
    }
}
