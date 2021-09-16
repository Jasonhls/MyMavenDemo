package com.cn.designMode.strategy;

import com.cn.designMode.strategy.behavior.FlyBehavior;
import com.cn.designMode.strategy.behavior.QuackBehavior;

public abstract class Duck {
    /**
     *属性，策略接口
     */
    FlyBehavior flyBehavior;
    /**
     *其他属性，也是策略接口
     */
    QuackBehavior quackBehavior;

    public Duck() {
    }

    public void fly() {
        if(flyBehavior != null) {
            flyBehavior.fly();
        }
    }

    public void quack() {
        if(quackBehavior != null) {
            quackBehavior.quack();
        }
    }

    public abstract void display();


    //set方法用于动态改变行为

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public QuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
