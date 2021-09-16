package com.cn.threadAndLock.threadDataTransport.byConsumerAndProduct;

/**
 * Created by Jason on 2019/2/26.
 */
public class MainTest {
    public static void main(String[] args){
        Shop shop = new Shop();
        Thread t1 = new Thread(new Producer(shop));
        t1.start();
        Thread t2 = new Thread(new Consumer(shop));
        t2.start();
    }
}
