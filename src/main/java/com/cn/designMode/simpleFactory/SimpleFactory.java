package com.cn.designMode.simpleFactory;

public class SimpleFactory {
    public static Pizza orderPizza(String orderType) {
        Pizza pizza = null;
        if("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        }else if("cheese".equals(orderType)) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        }else if("pepper".equals(orderType)) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
}
