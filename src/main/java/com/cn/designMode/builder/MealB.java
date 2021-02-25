package com.cn.designMode.builder;

public class MealB extends MealBuilder{
    @Override
    public MealBuilder readOrder() {
        meal.setOrderBy("b");
        return this;
    }

    @Override
    public MealBuilder buildFood() {
        meal.setFood("鸡翅");
        return this;
    }

    @Override
    public MealBuilder buildDrink() {
        meal.setDrink("柠檬果汁");
        return this;
    }

    @Override
    public MealBuilder sendMeal() {
        meal.setSendBy("服务员B");
        return this;
    }
}
