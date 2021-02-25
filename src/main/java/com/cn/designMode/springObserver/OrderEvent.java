package com.cn.designMode.springObserver;

import org.springframework.context.ApplicationEvent;

/**
 * 创建订单事件
 */
public class OrderEvent extends ApplicationEvent {
    public OrderEvent(Object source) {
        super(source);
        System.out.println("订单事件监听");
    }
}
