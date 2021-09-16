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
    public void testFour() throws ExecutionException, InterruptedException {
        Callable callable = new MyCallable(100);
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);
        Thread thread3 = new Thread(futureTask);
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(futureTask.get());
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

    @Test
    public void testFive() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> f1 = executorService.submit(new MyCallable(100));
        Future<Integer> f2 = executorService.submit(new MyCallable(50));
        System.out.println(f1.get());
        System.out.println(f2.get());
        executorService.shutdown();
    }

    class MyCallable implements Callable<Integer> {
        private int num;

        public MyCallable(int num) {
            this.num = num;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < num; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
