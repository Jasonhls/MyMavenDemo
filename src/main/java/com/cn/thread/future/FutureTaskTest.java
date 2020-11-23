package com.cn.thread.future;

import java.util.concurrent.*;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-02 14:06
 **/
public class FutureTaskTest {
    public static void main(String[] args) {
            ExecutorService es = Executors.newSingleThreadExecutor();
            Callable callable = new CallableDemo();
            FutureTask<Integer> futureTask = new FutureTask<>(callable);
            es.submit(futureTask);
            es.shutdown();
            try {
                System.out.println("主线程在执行其他任务");
                Thread.sleep(10000);

//            if(future.get()!=null){
                //输出获取到的结果
                System.out.println("future.get()-->"+futureTask.get());
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
