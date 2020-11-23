package com.cn.thread.future;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-02 14:11
 **/
public class CallableDemo implements Callable<Integer> {
    private int sum;

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable子线程开始计算啦！");
//        Thread.sleep(2000);
        int count = 5000;
        for(int i = 0 ; i < count; i++){
            sum = sum + i;
        }
        System.out.println("Callable子线程计算结束！");
        return sum;
    }
}
