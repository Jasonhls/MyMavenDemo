package com.cn.tracedemo.config;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/12/8 15:47
 */
public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    public MyThreadPoolTaskExecutor() {
        super();
    }

    /**
     * 重新实现三个常用方法
     */

    @Override
    public void execute(Runnable task) {
        super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }
}
