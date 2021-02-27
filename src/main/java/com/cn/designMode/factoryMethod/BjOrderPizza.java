package com.cn.designMode.factoryMethod;

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
