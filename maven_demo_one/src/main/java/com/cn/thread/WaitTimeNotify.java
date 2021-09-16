package com.cn.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-19 14:33
 **/
public class WaitTimeNotify {
    static boolean flag = true;
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thead1 = new Thread(new Wait());
        thead1.start();
        //保证wait先获取到obj的锁
        TimeUnit.SECONDS.sleep(1);
        Thread thread2 = new Thread(new Notify());
        thread2.start();
    }

    static class Wait implements Runnable{

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("执行wait start");
                while(flag){
                    try {
                        obj.wait(1000);
                        System.out.println("啦啦");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("执行wait end");
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("执行notify start");
                flag = false;
                obj.notifyAll();
                System.out.println("执行notify end");
            }
        }
    }

}
