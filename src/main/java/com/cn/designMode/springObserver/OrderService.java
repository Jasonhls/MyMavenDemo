package com.cn.designMode.springObserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    @Autowired
    ApplicationContext applicationContext;

    public void saveOrder(){
        OrderEvent orderEvent = new OrderEvent("");
        applicationContext.publishEvent(orderEvent);
        System.out.println("订单创建成功。。。。。。");
    }
}
