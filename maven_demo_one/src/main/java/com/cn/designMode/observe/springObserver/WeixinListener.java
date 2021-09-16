package com.cn.designMode.observe.springObserver;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class WeixinListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent orderEvent) {
        System.out.println("微信通知了");
    }
}
