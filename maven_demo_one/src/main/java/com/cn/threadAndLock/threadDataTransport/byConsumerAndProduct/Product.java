package com.cn.threadAndLock.threadDataTransport.byConsumerAndProduct;

/**
 * Created by Jason on 2019/2/26.
 */
public class Product {
    int id;

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}
