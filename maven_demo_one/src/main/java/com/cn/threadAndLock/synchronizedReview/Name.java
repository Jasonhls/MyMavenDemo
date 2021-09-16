package com.cn.threadAndLock.synchronizedReview;

/**
 * Created by Jason on 2019/2/22.
 * 线程同步主要使用synchronized关键字；具体有两种实现方式：1、作为关键字修饰类中一个方法；2、修饰方法中的一块区域(代码);
 */
public class Name {
    public synchronized String getName(){
        return "123";
    }

    public String getNameTwo(Object o){
        synchronized (o){//括号里面也可以用this
            return "123";
        }
    }
}
