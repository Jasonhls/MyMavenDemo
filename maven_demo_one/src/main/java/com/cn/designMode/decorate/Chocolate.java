package com.cn.designMode.decorate;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-01 10:50
 **/
public class Chocolate extends Decorator{

    public Chocolate(Drink drink) {
        super(drink);
        setDes("巧克力");
        setPrice(3.0f);
    }
}
