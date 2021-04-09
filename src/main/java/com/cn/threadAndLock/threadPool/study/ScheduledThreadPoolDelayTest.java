package com.cn.threadAndLock.threadPool.study;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 11:50
 **/
public class ScheduledThreadPoolDelayTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.schedule(() -> {
            System.out.println("task1");
            int i = 1 / 0;
        }, 1, TimeUnit.SECONDS);

        pool.schedule(() -> {
            System.out.println("task2");
        }, 1, TimeUnit.SECONDS);
    }
}
