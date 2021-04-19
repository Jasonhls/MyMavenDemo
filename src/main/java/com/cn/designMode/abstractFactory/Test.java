package com.cn.designMode.abstractFactory;

public class Test {
    public static void main(String[] args) {
        new OrderPizza(new BjPizzaFactory(), "cheese");
        new OrderPizza(new BjPizzaFactory(), "pepper");

        new OrderPizza(new LdPizzaFactory(), "pepper");
        new OrderPizza(new LdPizzaFactory(), "pepper");
    }
}
