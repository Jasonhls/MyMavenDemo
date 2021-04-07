package com.cn.threadAndLock.threadApi.interrupt;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 10:59
 **/
public class InterruptRunningThreadTest {
    /**
     * 打断正在运行状态的线程
     * @param args
     */
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            while(true) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("线程" + Thread.currentThread().getName() + "被打断了，停止运行");
                    break;
                }
            }
        }, "t1");

        t1.start();

        Thread.sleep(1000);

        System.out.println("开始打断t1线程");
        //打断t1线程
        t1.interrupt();
    }
}
