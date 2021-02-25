package com.cn.threadAndLock.AtomicAndLock;

import java.util.concurrent.locks.Lock;

/**
 * Created by Jason on 2019/3/2.
 */
public class LockDemoLock2 {
    volatile int value;

    //广泛应用与JUC并发编程工具包中
//    Lock lock = new ReentrantLock();
    Lock lock = new NetEaseLock();

    public void add(){
        lock.lock();//获取锁（一直等，直到获取锁）
//        lock.tryLock(10, TimeUnit.SECONDS);获取锁（一直等，到了10秒就不等了）
//        lock.tryLock();等一次e
        try {
            value++;
        } finally {
            lock.unlock();//释放锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemoLock2 d = new LockDemoLock2();
        for (int i = 0;i < 2;i++){
            new Thread(
                    () -> {
                      for (int j = 0;j < 10000;j++){
                          d.add();
                      }
                    }
            ).start();
        }
        Thread.sleep(2000L);//这里让主线程睡2秒，不急着打印d.value，因为两个副线程可能还没执行完，等他们两都执行完，再打印d.value
        System.out.println(d.value);
    }
}
