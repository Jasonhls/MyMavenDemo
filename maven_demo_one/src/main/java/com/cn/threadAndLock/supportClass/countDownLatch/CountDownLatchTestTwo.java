package com.cn.threadAndLock.supportClass.countDownLatch;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jason on 2018/10/17.
 */
@Component
public class CountDownLatchTestTwo {
    public void testCountDownLatchDemo() throws InterruptedException{
        ThreadPoolExecutor threadPoolTaskExecutor = new ThreadPoolExecutor(5,10,100, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
        int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0;i < count;i++){
            threadPoolTaskExecutor.execute(new MyRunnable(latch,i));
        }

        latch.await();
        System.out.println("等待线程被唤醒!");
        threadPoolTaskExecutor.shutdown();
    }

    class MyRunnable implements Runnable{
        CountDownLatch latch = null;
        int i;

        public MyRunnable(CountDownLatch latch, int i) {
            this.latch = latch;
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("线程" + i + "完成了操作...");
            Thread.currentThread();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
