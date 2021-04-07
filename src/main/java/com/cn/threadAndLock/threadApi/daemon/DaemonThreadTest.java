package com.cn.threadAndLock.threadApi.daemon;

public class DaemonThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true) {
                if(Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "t1");

        //设置为守护线程
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "结束了");
    }
}
