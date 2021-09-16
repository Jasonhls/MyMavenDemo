package com.cn.threadAndLock.threadDesignMode.orderControl;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 11:45
 **/
public class OrderControlTest {
    static final Object lock = new Object();
    static boolean t2runned = false;

    /**
     * 保证先执行线程2，再执行线程1
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while(!t2runned) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("1");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(2);
                t2runned = true;
                lock.notifyAll();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
