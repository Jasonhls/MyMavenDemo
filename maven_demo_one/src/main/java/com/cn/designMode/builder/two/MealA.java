package com.cn.designMode.builder.two;

public class MealA extends MealBuilder{
    @Override
    public MealBuilder readOrder() {
        meal.setOrderBy("a");
        return this;
    }

    @Override
    public MealBuilder buildFood() {
        meal.setFood("薯条");
        return this;
    }

    @Override
    public MealBuilder buildDrink() {
        meal.setDrink("可乐");
        return this;
    }

    @Override
    public MealBuilder sendMeal() {
        meal.setSendBy("服务员A");
        return this;
    }
}
