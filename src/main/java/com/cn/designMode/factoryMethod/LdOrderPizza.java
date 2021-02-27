package com.cn.designMode.factoryMethod;

import com.cn.designMode.factoryMethod.pizza.LdCheesePizza;
import com.cn.designMode.factoryMethod.pizza.LdPepperPizza;
import com.cn.designMode.factoryMethod.pizza.Pizza;

public class LdOrderPizza extends OrderPizza{

    public LdOrderPizza(String orderType) {
        super(orderType);
    }

    @Override
    protected Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if("cheese".equals(orderType)) {
            pizza = new LdCheesePizza();
        }else if("pepper".equals(orderType)) {
            pizza = new LdPepperPizza();
        }
        return pizza;
    }
}
