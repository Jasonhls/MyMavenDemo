package com.cn.threadAndLock.threadPool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Jason on 2019/3/13.
 */
public class MaxThreadsDemo {
    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            Thread.sleep(20000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0;i < 5000;i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println("i=" + i);
        }
    }
}
