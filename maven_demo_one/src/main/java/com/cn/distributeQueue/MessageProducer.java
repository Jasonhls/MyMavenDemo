package com.cn.distributeQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Object message){
        log.info("to send message:{}",message);
        amqpTemplate.convertAndSend(RabbitmqConfig.DIRECT_ROUTING_KEY_sendqueue,message);
    }
}
