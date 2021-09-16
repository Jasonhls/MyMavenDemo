package com.cn.designMode.factoryMethod;

public class Test {
    public static void main(String[] args) {
        new BjOrderPizza("cheese");
        new BjOrderPizza("pepper");

        new LdOrderPizza("cheese");
        new LdOrderPizza("pepper");
    }
}
