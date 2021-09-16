package com.cn.threadAndLock.lock.aqs;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 15:23
 **/
public class TestAqs {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "，locking...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                System.out.println(Thread.currentThread().getName() + "，unlocking...");
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "，locking...");
            }finally {
                System.out.println(Thread.currentThread().getName() + "，unlocking...");
                lock.unlock();
            }
        }, "t2").start();
    }
}
