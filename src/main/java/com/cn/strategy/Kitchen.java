package com.cn.strategy;

/**
 * @description: kitchen
 * @author: wangqiang
 * @create: 2020-04-21 20:55
 **/
public class Kitchen {
    private CrabCooking crabCooking;

    public CrabCooking getCrabCooking() {
        return crabCooking;
    }

    public void setCrabCooking(CrabCooking crabCooking) {
        this.crabCooking = crabCooking;
    }

    public void cookingMethod(){
        crabCooking.cookingMethod();
    }
}
