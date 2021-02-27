package com.cn.designMode.abstractFactory.pizza;

import com.cn.designMode.abstractFactory.AbstractFactory;
import com.cn.designMode.abstractFactory.LdPepperPizza;

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
