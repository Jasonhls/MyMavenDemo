package com.cn.designMode.abstractFactory.pizza;

public class LdPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦胡椒披萨");
        System.out.println("给制作伦敦胡椒披萨准备原材料");
    }
}
