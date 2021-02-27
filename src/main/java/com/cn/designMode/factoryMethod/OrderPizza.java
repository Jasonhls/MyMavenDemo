package com.cn.designMode.factoryMethod;

public abstract class OrderPizza {

    public OrderPizza() {

    }

    public OrderPizza(String orderType) {
        Pizza pizza = createPizza(orderType);
        if(pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.box();
            pizza.cut();
        }
    }

    protected abstract Pizza createPizza(String orderType);


}
