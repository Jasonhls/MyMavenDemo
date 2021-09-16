package com.cn.designMode.decorate;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-01 10:42
 **/
public abstract class Drink {
    /**
     * 描述
     */
    private String des;
    private float price = 0.0f;

    /**
     * 计算费用的抽象方法
     * 子类来实现
     * @return
     */
    public abstract float cost();




    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
