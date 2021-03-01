package com.cn.designMode.decorate;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-01 10:43
 **/
public class Coffee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
