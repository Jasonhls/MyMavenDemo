package com.cn.strategy;

/**
 * @description: b
 * @author: wangqiang
 * @create: 2020-04-21 20:23
 **/
public class BbFish extends JLabel implements CrabCooking{
    @Override
    public void cookingMethod() {
        this.setIcon(new Icon("src/strategy/b.jpg"));
        this.setHorizontalAlignment("center");
    }
}
