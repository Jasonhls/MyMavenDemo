package com.cn.threadAndLock.AtomicAndLock;

/**
 * Created by Jason on 2019/3/2.
 */
public class LockDemo1 {
    volatile int value;//volatile只是表示可见性，并不能保证原子性

    public void add(){
        value++;
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo1 d = new LockDemo1();
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
