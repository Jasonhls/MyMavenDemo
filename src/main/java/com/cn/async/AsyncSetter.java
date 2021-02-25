package com.cn.async;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by Jason on 2018/10/18.
 */
public class AsyncSetter<T> {
    List<Consumer<T>> consumers = new ArrayList<>();

    private Executor executor;

    private T originalObj;

    private int timeOut;

    private AtomicInteger successJobNum = new AtomicInteger(0);//AtomicInteger则通过一种线程安全的加减操作接口

    public AsyncSetter() {
    }

    public AsyncSetter(Executor executor, int timeOut) {
        this.executor = executor;
        this.timeOut = timeOut;
    }

    public void execute(){
        execute(timeOut,TimeUnit.SECONDS);
    }

    public void execute(int timeOut, TimeUnit timeUnit){
        System.out.println("AsynacSetter" + this.hashCode() + "开始执行，任务数" + consumers.size());
        StopWatch stopWatch = new StopWatch();//用于统计时间的
        stopWatch.start("执行第 " + consumers.size() + " 个任务");
        CountDownLatch countDownLatch = new CountDownLatch(consumers.size());
        for (int i = 0;i < consumers.size();i++){
            final int m = i;
            executor.execute(() ->{
                StopWatch sw = new StopWatch();
                try {
                    sw.start("执行第" + m + "个任务");
                    consumers.get(m).accept(originalObj);
                    successJobNum.getAndDecrement();//减一，对应的successJobNum.getAndIncrement()是加一
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();//减一
                    sw.stop();
                    System.out.println("sw:" + sw.prettyPrint());
                }
            });
        }
        try {
            boolean await = countDownLatch.await(timeOut, timeUnit);
            stopWatch.stop();//时间统计停止
            System.out.println("stopWatch:"+stopWatch.prettyPrint());
            System.out.println("AsyncSetter"+this.hashCode()+"执行结束");
            if(!await){
                System.err.println("AsyncSetter"+this.hashCode()+"有未完成的任务");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();//中断当前线程
        }
    }

    public AsyncSetter<T> setOriginalObj(T originalObj){
        this.originalObj = originalObj;
        return this;
    }

    public AsyncSetter<T> addRunnable(Consumer<T> c){
        consumers.add(c);
        return this;
    }

    public T getOriginalObj(){
        return originalObj;
    }
}
