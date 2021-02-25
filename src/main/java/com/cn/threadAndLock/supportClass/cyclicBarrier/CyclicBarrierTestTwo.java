package com.cn.threadAndLock.supportClass.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Jason on 2019/2/26.
 * 当所有线程线程写入操作完毕之后，所有线程就继续进行后续的操作了。
 　　如果说想在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数
 从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
 */
public class CyclicBarrierTestTwo {
    public static void main(String[] args){
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程名称：" + Thread.currentThread().getName());
            }
        });

        for (int i = 0;i < N;i++){
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在写入数据");
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入数据完");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续执行其他任务");
        }
    }
}
