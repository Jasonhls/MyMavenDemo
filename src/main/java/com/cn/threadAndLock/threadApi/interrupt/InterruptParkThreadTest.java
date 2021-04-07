package com.cn.threadAndLock.threadApi.interrupt;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 11:30
 **/
public class InterruptParkThreadTest {
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            System.out.println("park...");
            LockSupport.park();
            System.out.println("unpark...");
            System.out.println("打断状态：" + Thread.currentThread().isInterrupted());

            //LockSupport.park()在当前线程的打断状态为true的时候会失效，只有打断状态为false，才会暂停当前线程。
            Thread.interrupted();//获取当前线程打断状态，并清空当前线程的打断状态
            LockSupport.park();
            System.out.println("park again ...");
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        t1.interrupt();
    }
}
