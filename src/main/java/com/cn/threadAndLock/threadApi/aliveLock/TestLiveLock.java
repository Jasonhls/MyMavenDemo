package com.cn.threadAndLock.threadApi.aliveLock;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 15:14
 **/
public class TestLiveLock {
    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            //期望 减到 0 退出循环
            while(count > 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println("count " + count);
            }
        }, "t1").start();

        new Thread(() -> {
            //期望超过 20 退出循环
            while(count < 20) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println("count " + count);
            }
        }, "t2").start();
    }
}
