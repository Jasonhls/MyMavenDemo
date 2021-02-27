package com.cn.designMode.factoryMethod;

public class LdCheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("伦敦奶酪披萨");
        System.out.println("给制作伦敦奶酪披萨准备原材料");
    }
}
