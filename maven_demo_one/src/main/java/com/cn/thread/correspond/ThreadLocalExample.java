package com.cn.thread.correspond;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-24 15:00
 **/
public class ThreadLocalExample {

    static class MyRunnable implements Runnable {
        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            threadLocal.set((int)(Math.random() * 1000));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程名为："+Thread.currentThread().getName()+"，获取结果为：" + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.start();
        t2.start();
    }
}
