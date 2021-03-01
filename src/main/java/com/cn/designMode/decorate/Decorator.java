package com.cn.designMode.decorate;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-01 10:47
 **/
public class Decorator extends Drink{

    private Drink drink;

    public Decorator(Drink drink) { //组合
        this.drink = drink;
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDes() {
        return super.getDes() + " " + super.getPrice() + " && " + drink.getDes() + " " + drink.getPrice();
    }
}
