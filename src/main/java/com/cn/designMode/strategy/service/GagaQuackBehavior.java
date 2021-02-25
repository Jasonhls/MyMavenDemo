package com.cn.designMode.strategy.service;

public class GagaQuackBehavior implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("gaga quack");
    }
}
