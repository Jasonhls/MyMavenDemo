package com.cn.strategy;

/**
 * @description: a
 * @author: wangqiang
 * @create: 2020-04-21 20:22
 **/
public class AaFish extends JLabel implements CrabCooking{
    @Override
    public void cookingMethod() {
        this.setIcon(new Icon("src/strategy/a.jpg"));
        this.setHorizontalAlignment("center");
    }
}
