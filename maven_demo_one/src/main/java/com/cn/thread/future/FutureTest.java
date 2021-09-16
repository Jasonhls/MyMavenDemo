package com.cn.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-02 14:10
 **/
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable callable = new CallableDemo();
        Future future = executorService.submit(callable);
        executorService.shutdown();
        try {
            System.out.println("主线程在执行其他任务");
            Thread.sleep(10000);

//            if(future.get()!=null){
                //输出获取到的结果
                System.out.println("future.get()-->"+future.get());
//            }else{
//                //输出获取到的结果
//                System.out.println("future.get()未获取到结果");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }
}
