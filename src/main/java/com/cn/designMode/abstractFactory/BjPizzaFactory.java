package com.cn.designMode.abstractFactory;

import com.cn.designMode.abstractFactory.pizza.BjCheesePizza;
import com.cn.designMode.abstractFactory.pizza.BjPepperPizza;
import com.cn.designMode.abstractFactory.pizza.Pizza;

public class BjPizzaFactory implements AbstractFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if("cheese".equals(orderType)) {
            pizza = new BjCheesePizza();
        }else if("pepper".equals(orderType)) {
            pizza = new BjPepperPizza();
        }
        return pizza;
    }
}
