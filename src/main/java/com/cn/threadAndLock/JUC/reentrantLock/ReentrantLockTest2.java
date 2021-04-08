package com.cn.threadAndLock.JUC.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 15:44
 **/
public class ReentrantLockTest2 {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(() -> {
           try {
               //如果没有竞争那么此方法就会获取lock对象锁
               //如果有竞争就进入阻塞队列，可以被其他线程用interrupt方法打断
               System.out.println("尝试获取锁");
               lock.lockInterruptibly();
           } catch (InterruptedException e) {
               e.printStackTrace();
               System.out.println("没有获得锁，返回");
               return;
           }
           try {
               System.out.println("获取到了锁");
           }finally {
               lock.unlock();
           }
       }, "t1");

       lock.lock();
       t1.start();

       Thread.sleep(1000);
       t1.interrupt();
    }
}
