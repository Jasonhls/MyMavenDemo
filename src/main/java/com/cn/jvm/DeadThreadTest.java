package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 14:02
 **/
public class DeadThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + "结束");
        };

        Thread t1 = new Thread(r, "线程1");
        Thread t2 = new Thread(r, "线程2");

        t1.start();
        t2.start();
    }


}

class DeadThread {
    static {
        if(true) {
            System.out.println(Thread.currentThread().getName() + "初始化当前类");
            /*while(true) {

            }*/
        }
    }
}
