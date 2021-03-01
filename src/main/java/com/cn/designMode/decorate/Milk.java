package com.cn.designMode.decorate;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-01 10:51
 **/
public class Milk extends Decorator{
    public Milk(Drink drink) {
        super(drink);
        setDes("牛奶");
        setPrice(6.0f);
    }
}
