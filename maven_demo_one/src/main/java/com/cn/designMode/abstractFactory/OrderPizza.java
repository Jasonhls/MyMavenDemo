package com.cn.designMode.abstractFactory;

import com.cn.designMode.abstractFactory.pizza.Pizza;

public class OrderPizza {
    AbstractFactory factory;

    public void setAbstractFactory(AbstractFactory factory) {
        this.factory = factory;
    }

    public OrderPizza() {

    }

    public OrderPizza(AbstractFactory factory, String orderType) {
        this.factory = factory;
        createPizza(orderType);
    }

    public void createPizza(String orderType) {
        Pizza pizza = factory.createPizza(orderType);
        if(pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.box();
            pizza.cut();
        }
    }

}
