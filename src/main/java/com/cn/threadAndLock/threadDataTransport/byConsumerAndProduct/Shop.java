package com.cn.threadAndLock.threadDataTransport.byConsumerAndProduct;

/**
 * Created by Jason on 2019/2/26.
 */
public class Shop {
    //默认为0个产品
    int nIndex = 0;
    Product[] pro = new Product[10];

    //产品交给店员
    public synchronized void addProduct(Product pd){
        while(nIndex == pro.length){
            System.out.println("生产处：" + nIndex);
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        pro[nIndex] = pd;
        nIndex++;
    }

    //消费者从店员处取走产品
    public synchronized Product getProduct(){
        while(nIndex == 0){
            System.out.println("消费处" + nIndex);
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        nIndex--;
        return pro[nIndex];
    }
}
