package com.cn.threadAndLock.threadApi.status;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 11:00
 **/
public class ThreadStatusTest2 {
    final static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                System.out.println("t1执行");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1其他代码");//这里打断点，Suspend设置为Thread模式
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println("t2执行");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2其他代码");//这里打断点，Suspend设置为Thread模式
            }
        }, "t2").start();

        Thread.sleep(500);
        System.out.println("唤醒obj上的其他线程");
        synchronized (obj) {
            obj.notifyAll();//这里打断点，Suspend设置为Thread模式
        }
    }
}
