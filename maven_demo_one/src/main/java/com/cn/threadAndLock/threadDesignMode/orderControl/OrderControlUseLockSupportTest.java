package com.cn.threadAndLock.threadDesignMode.orderControl;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 12:20
 **/
public class OrderControlUseLockSupportTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("1");
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("2");
            LockSupport.unpark(t1);
        }, "t2");
        t2.start();
    }
}
