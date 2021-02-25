package com.cn.designMode.builder;

/**
 * 指挥者
 */
public class KFCDirect {
    private MealBuilder mealBuilder;

    public KFCDirect(MealBuilder mealBuilder){
        this.mealBuilder = mealBuilder;
    }

    public Meal construct(){
        return mealBuilder.readOrder()
                .buildFood()
                .buildDrink()
                .sendMeal().getMeal();
    }
}
