package com.cn.thread.currentTools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrierAction），
 * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场
 * @author: helisen
 * @create: 2020-06-02 11:31
 **/
public class CyclicBarrierTest2 {

    static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();;

        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
