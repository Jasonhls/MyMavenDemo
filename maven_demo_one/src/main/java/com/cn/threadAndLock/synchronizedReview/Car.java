package com.cn.threadAndLock.synchronizedReview;

/**
 * Created by Jason on 2019/2/22.
 */
public class Car {
    /*私有构造方法*/
    private Car() {
    }

    /*定义一个静态的私有的空的car常量*/
    private static Car car = null;

    /*对外开放一个静态的共有的方法用来获取实例*/
    public Car getInstance(){
        if(car == null){
            synchronized (Car.class){
                if(car == null){
                    car = new Car();
                }
            }
        }
        return car;
    }
}
