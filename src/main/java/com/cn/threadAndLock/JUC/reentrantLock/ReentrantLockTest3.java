package com.cn.threadAndLock.JUC.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 15:44
 **/
public class ReentrantLockTest3 {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(() -> {
           System.out.println(Thread.currentThread().getName() + "尝试获取锁");
           try {
               //tryLock方法也支持被打断，所以这里会抛出异常
               if(!lock.tryLock(2, TimeUnit.SECONDS)) {
                   System.out.println(Thread.currentThread().getName() + "获取不到锁");
                   return;
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
               System.out.println(Thread.currentThread().getName() + "也没有获取到锁");
               return;
           }
           try {
               System.out.println(Thread.currentThread().getName() + "获取到了锁");
           }finally {
               lock.unlock();
           }
       }, "t1");

       lock.lock();
        System.out.println(Thread.currentThread().getName() + "获得锁");
       t1.start();

       Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "释放了锁");
       lock.unlock();
    }
}
