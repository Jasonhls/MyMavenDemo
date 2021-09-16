package com.cn.threadAndLock.threadDataTransport.byConsumerAndProduct;

/**
 * Created by Jason on 2019/2/26.
 */
public class Producer implements Runnable{
    Shop shop;

    public Producer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0;i < 15;i++){
            Product pro = new Product(i);
            shop.addProduct(pro);
            System.out.println("生产了：" + pro);
            //模拟生产一件产品花费的时间
            try {
                Thread.sleep((long)Math.random()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
