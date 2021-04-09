package com.cn.threadAndLock.threadPool.study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 12:23
 **/
public class ThreadPoolExceptionTest {
    public static void main(String[] args) throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> f = pool.submit(() -> {
            System.out.println("task1");
            //这里出异常，会被封装到Future对象中，可以通过get()获取得到
            int i = 1 / 0;
            return true;
        });
        System.out.println(f.get());
    }
}
