package com.cn.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-18 13:37
 **/
public class Profiler {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static final void begin() {
        threadLocal.set(System.currentTimeMillis());
    }

    public static final Long end() {
        return System.currentTimeMillis() - threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost " + Profiler.end() + "mills");
    }
}
