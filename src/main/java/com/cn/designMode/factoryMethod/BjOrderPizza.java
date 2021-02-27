package com.cn.designMode.factoryMethod;

import com.cn.designMode.factoryMethod.pizza.BjCheesePizza;
import com.cn.designMode.factoryMethod.pizza.BjPepperPizza;
import com.cn.designMode.factoryMethod.pizza.Pizza;

public class BjOrderPizza extends OrderPizza{

    public BjOrderPizza(String orderType) {
        super(orderType);
    }

    @Override
    protected Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if("cheese".equals(orderType)) {
            pizza = new BjCheesePizza();
        }else if("pepper".equals(orderType)) {
            pizza = new BjPepperPizza();
        }
        return pizza;
    }
}
