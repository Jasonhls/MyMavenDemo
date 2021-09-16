package com.cn.threadAndLock.supportClass.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Jason on 2019/2/26.
 * CountDownLatch类只提供了一个构造器：
 public CountDownLatch(int count) {  };  //参数count为计数值
 　　然后下面这3个方法是CountDownLatch类中最重要的方法：
 1.public void await() throws InterruptedException { };   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 2.public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 3.public void countDown() { };  //将count值减1
 */
public class CountDownLatchTest {
    public static void main(String[] args){
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(){
            public void run(){
                try {
                    System.out.println("子线程：" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            public void run(){
                try {
                    System.out.println("子线程：" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(4000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            System.out.println("等待两个子线程执行完");
            latch.await();//让主线程挂起，这个时候两个子线程就会继续执行，等待latch的值为0的时候，主线程才会接着执行
            System.out.println("两个子线程已经执行完毕");
            System.out.println("继续执行子线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
