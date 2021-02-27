package com.cn.designMode.builder.two;

/**
 * 抽象建造者
 */
public abstract class MealBuilder {
    Meal meal = new Meal();
    public abstract MealBuilder readOrder();
    public abstract MealBuilder buildFood();
    public abstract MealBuilder buildDrink();
    public abstract MealBuilder sendMeal();

    public Meal getMeal(){
        return meal;
    }
}
