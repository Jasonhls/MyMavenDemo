package com.cn.designMode.factoryMethod;

public class BjPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京胡椒披萨");
        System.out.println("给制作北京胡椒披萨准备原材料");
    }
}
