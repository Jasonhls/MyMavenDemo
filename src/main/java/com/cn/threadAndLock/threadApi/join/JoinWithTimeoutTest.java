package com.cn.threadAndLock.threadApi.join;

public class JoinWithTimeoutTest {
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        }, "t1");

        long start = System.currentTimeMillis();
        t1.start();

        System.out.println("join start");
        t1.join(3000);
        long end = System.currentTimeMillis();
        System.out.println("r1:" + r1 + ", r2:" + r2 + ", 耗时：" + (end - start));
    }
}
