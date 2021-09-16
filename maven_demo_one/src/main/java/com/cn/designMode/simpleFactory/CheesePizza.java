package com.cn.designMode.simpleFactory;

public class CheesePizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("给制造奶酪披萨准备原材料");
    }
}
