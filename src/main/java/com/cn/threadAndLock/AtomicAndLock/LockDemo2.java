package com.cn.threadAndLock.AtomicAndLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jason on 2019/3/2.
 */
public class LockDemo2 {
    AtomicInteger value = new AtomicInteger(0);

    public void add(){
        //原子操作
        value.incrementAndGet();//加一操作
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo2 d = new LockDemo2();
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
