package com.cn.thread.interruptThread;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-23 18:16
 **/
public class ExampleThread extends Thread {

    volatile boolean stop = false;

    @Override
    public void run() {
        while(!stop) {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            //实现每隔一秒打印一次Thread is running...
            while((System.currentTimeMillis() - time < 1000) && (!stop)) {
            }
        }
        System.out.println("Thread exiting under request...");
    }

    public static void main(String[] args) throws Exception {
        ExampleThread thread = new ExampleThread();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(5000);
        System.out.println("Asking thread to stop...");

        thread.stop = true;
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }
}
