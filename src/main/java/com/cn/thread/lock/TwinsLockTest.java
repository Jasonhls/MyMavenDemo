package com.cn.thread.lock;

import com.cn.utils.SleepUtils;
import org.junit.Test;

/**
 * @description: 每次只允许两个线程同时抢到锁，并发执行代码
 * @author: helisen
 * @create: 2020-05-28 10:10
 **/
public class TwinsLockTest {
    @Test
    public void test() {
        final TwinsLock lock = new TwinsLock();

        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        //启动10个线程
        for (int i = 0; i < 10 ; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
        //每隔1秒换行
//        for (int i = 0; i < 10 ; i++) {
//            SleepUtils.second(1);
//            System.out.println("---------------");
//        }
        SleepUtils.second(30);
    }
}
