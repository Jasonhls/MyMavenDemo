package com.cn.designMode.builder.two;

public class BuilderTest {
    public static void main(String[] args) {
        MealA mealA = new MealA();
        KFCDirect kfcDirect = new KFCDirect(mealA);
        Meal meal = kfcDirect.construct();
        System.out.println("用户A用餐过程:"+meal.getSendBy() + " " + meal.getFood() + " " + meal.getDrink() + " " + meal.getSendBy());
    }
}
