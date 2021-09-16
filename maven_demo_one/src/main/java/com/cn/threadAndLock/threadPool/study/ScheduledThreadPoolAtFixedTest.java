package com.cn.threadAndLock.threadPool.study;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 11:54
 **/
public class ScheduledThreadPoolAtFixedTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(() -> {
            System.out.println("哈哈");
        }, 1, 1, TimeUnit.SECONDS);
    }
}
