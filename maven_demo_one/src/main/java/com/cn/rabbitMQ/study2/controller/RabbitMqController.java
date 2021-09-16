package com.cn.rabbitMQ.study2.controller;

import com.cn.rabbitMQ.study2.RabbitmqOrder;
import com.cn.rabbitMQ.study2.config.DelaySender;
import com.cn.rabbitMQ.study2.config.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jason on 2019/3/28.
 */
@RestController
@RequestMapping(value = "/rabbitmq/test")
public class RabbitMqController {
    @Autowired
    private Sender sender;
    @Autowired
    private DelaySender delaySender;

    @GetMapping("/sendDirectQueue")
    public Object sendDirectQueue() {
        sender.sendDirectQueue();
        return "ok";
    }

    @GetMapping("/sendTopic")
    public Object sendTopic() {
        sender.sendTopic();
        return "ok";
    }

    @GetMapping("/sendFanout")
    public Object sendFanout() {
        sender.sendFanout();
        return "ok";
    }



    @GetMapping("/sendDelay")
    public Object sendDelay() {
        RabbitmqOrder rabbitmqOrder1 = new RabbitmqOrder();
        rabbitmqOrder1.setOrderStatus(0);
        rabbitmqOrder1.setOrderId("123456");
        rabbitmqOrder1.setOrderName("小米6");

        RabbitmqOrder rabbitmqOrder2 = new RabbitmqOrder();
        rabbitmqOrder2.setOrderStatus(1);
        rabbitmqOrder2.setOrderId("456789");
        rabbitmqOrder2.setOrderName("小米8");

        delaySender.sendDelay(rabbitmqOrder1);
        delaySender.sendDelay(rabbitmqOrder2);
        return "ok";
    }
}
