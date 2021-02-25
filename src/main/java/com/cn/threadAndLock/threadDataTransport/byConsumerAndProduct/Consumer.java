package com.cn.threadAndLock.threadDataTransport.byConsumerAndProduct;

/**
 * Created by Jason on 2019/2/26.
 */
public class Consumer implements Runnable{
    Shop shop;

    public Consumer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0;i < 15;i++){
            Product product = shop.getProduct();
            System.out.println("消费了：" + product);
            //模拟消费一件产品花费的时间
            try {
                Thread.sleep((long)Math.random()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
