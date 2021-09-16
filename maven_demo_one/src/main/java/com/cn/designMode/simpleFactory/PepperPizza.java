package com.cn.designMode.simpleFactory;

public class PepperPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("给制造胡椒披萨准备原材料");
    }
}
