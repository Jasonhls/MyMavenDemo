package com.cn.designMode.simpleFactory;

public class OrderPizza {
    public static void main(String[] args) {
        String orderType = "cheese";
        Pizza pizza = SimpleFactory.orderPizza(orderType);
        if(pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.box();
            pizza.cut();
        }
    }
}
