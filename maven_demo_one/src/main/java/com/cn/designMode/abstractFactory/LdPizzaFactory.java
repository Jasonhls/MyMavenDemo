package com.cn.designMode.abstractFactory;

import com.cn.designMode.abstractFactory.pizza.LdCheesePizza;
import com.cn.designMode.abstractFactory.pizza.LdPepperPizza;
import com.cn.designMode.abstractFactory.pizza.Pizza;

public class LdPizzaFactory implements AbstractFactory {


    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if("cheese".equals(orderType)) {
            pizza = new LdCheesePizza();
        }else if("pepper".equals(orderType)) {
            pizza = new LdPepperPizza();
        }
        return pizza;
    }
}
