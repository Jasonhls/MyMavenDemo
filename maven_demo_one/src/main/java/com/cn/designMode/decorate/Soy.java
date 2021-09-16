package com.cn.designMode.decorate;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-01 10:51
 **/
public class Soy extends Decorator{
    public Soy(Drink drink) {
        super(drink);
        setDes("豆浆");
        setPrice(1.5f);
    }
}
