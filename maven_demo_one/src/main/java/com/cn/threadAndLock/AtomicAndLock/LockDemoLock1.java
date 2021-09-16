package com.cn.threadAndLock.AtomicAndLock;

/**
 * Created by Jason on 2019/3/2.
 */
public class LockDemoLock1 {
    volatile int value;

    public void add(){
        synchronized (this){
            value++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemoLock1 d = new LockDemoLock1();
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
