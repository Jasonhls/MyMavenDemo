package com.cn.threadAndLock.threadApi.status;

public class ThreadStatusTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("running...");
        }, "t1");

        Thread t2 = new Thread(() -> {
            while(true) {

            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            System.out.println("running...");
        }, "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (ThreadStatusTest.class) {
                try {
                    Thread.sleep(100000);  //timed_waiting状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join(); //需要等待线程2运行完，才能继续运行当前线程，因此当前线程状态为waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (ThreadStatusTest.class) {  //由于t4线程拿到了锁，这里就拿不到锁，处于blocked状态
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t6.start();

        System.out.println(t1.getState());//NEW
        System.out.println(t2.getState());//RUNNABLE
        System.out.println(t3.getState());//TERMINATED
        System.out.println(t4.getState());//TIMED_WAITING
        System.out.println(t5.getState());//WAITING
        System.out.println(t6.getState());//BLOCKED
    }
}
