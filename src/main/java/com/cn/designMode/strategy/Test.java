package com.cn.designMode.strategy;

import com.cn.designMode.strategy.behavior.impl.GegeQuackBehavior;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 10:47
 **/
public class Test {
    public static void main(String[] args) {

        Duck pekingDuck = new PekingDuck();
        pekingDuck.display();
        pekingDuck.fly();
        pekingDuck.quack();

        //改变北京烤鸭的行为
        System.out.println("------改变北京烤鸭行为------");
        pekingDuck.setQuackBehavior(new GegeQuackBehavior());
        pekingDuck.quack();

        System.out.println("-----------------");

        Duck wildDuck = new WildDuck();
        wildDuck.display();
        wildDuck.fly();
        wildDuck.quack();

        System.out.println("-----------------");

        Duck toyDuck = new ToyDuck();
        toyDuck.display();
        toyDuck.fly();
        toyDuck.quack();

    }
}
