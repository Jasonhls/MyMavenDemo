package com.cn.designMode.springObserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/springObserver")
public class SpringObserverController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order")
    public void order(){
        System.out.println("订单开始创建。。。。。。");
        orderService.saveOrder();
    }
}
