package com.cn.threadAndLock.threadPool.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 11:17
 **/
public class TestSubmit1 {
    public static void main(String[] args) throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "ok";
            }
        });
        System.out.println(future.get());
    }
}
