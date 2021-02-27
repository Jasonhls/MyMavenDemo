package com.cn.designMode.abstractFactory;

import com.cn.designMode.abstractFactory.pizza.LdPizzaFactory;

public class Test {
    public static void main(String[] args) {
        new OrderPizza(new BjPizzaFactory(), "cheese");
        new OrderPizza(new BjPizzaFactory(), "pepper");

        new OrderPizza(new LdPizzaFactory(), "pepper");
        new OrderPizza(new LdPizzaFactory(), "pepper");
    }
}
