package com.cn.threadAndLock.threadApi.join;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 18:21
 **/
public class JoinTest {

    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2 = 20;
        });

        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        System.out.println("join begin");
        t1.join();
        System.out.println("t1 join end");
        t2.join();
        System.out.println("t2 join end");
        long end = System.currentTimeMillis();
        System.out.println("r1" + r1 + ", r2" + r2  + "，耗时：" + (end - start));
    }
}
