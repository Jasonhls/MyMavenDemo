package com.cn.threadAndLock.lock.condition;

/**
 * Created by Jason on 2019/2/28.
 */
public class BoundedBufferTest {
    public static void main(String[] args){
        BoundedBuffer boundedBuffer = new BoundedBuffer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 1000;i++){
                    try {
                        boundedBuffer.put("" + i);
                        System.out.println("放入数据后putptr的值：" + boundedBuffer.putptr + ";count值：" + boundedBuffer.count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 1000;i++){
                    try {
                        Object take = boundedBuffer.take();
                        System.out.println("取出数据后takeptr的值："+ boundedBuffer.takeptr + ";count值：" + boundedBuffer.count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }


}
