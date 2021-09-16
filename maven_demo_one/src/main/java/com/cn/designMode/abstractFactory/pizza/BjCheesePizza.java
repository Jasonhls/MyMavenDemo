package com.cn.designMode.abstractFactory.pizza;

public class BjCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京奶酪披萨");
        System.out.println("给制造北京奶酪披萨准备原材料");
    }
}
