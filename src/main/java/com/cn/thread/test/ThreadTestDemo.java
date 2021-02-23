package com.cn.thread.test;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-23 14:11
 **/
public class ThreadTestDemo {
    @Test
    public void testOne() {
        MyThreadOne myThreadOne = new MyThreadOne();
        myThreadOne.start();
    }

    @Test
    public void testTwo() {
        long start = System.currentTimeMillis();
        MyThreadTwo myRunnable = new MyThreadTwo();
        new Thread(myRunnable).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("我是主线程，主线程花费时间为：" + (end - start));
    }

    @Test
    public void testThree() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池执行线程...");
            }
        });
    }

    @Test
    public void testFour() {
        Callable callable = new MyThreadFour();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class MyThreadOne extends Thread {
        @Override
        public void run() {
            System.out.println("MyThreadOne run...");
        }
    }

    class MyThreadTwo implements Runnable {
        @Override
        public void run() {
            try {
                //等待两秒
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThreadTwo run...");
        }
    }

    class MyThreadFour implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello";
        }
    }
}
