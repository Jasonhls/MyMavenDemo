package com.cn.rabbitMQ.study2.config;

import com.cn.rabbitMQ.study2.RabbitmqUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jason on 2019/3/25.
 */
@Component
@Slf4j
public class Receiver {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiverDirectQueue(RabbitmqUser rabbitmqUser){
        log.info("【receiverDirectQueue监听到消息】" + rabbitmqUser.toString());
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE1)
    public void receiverTopic1(RabbitmqUser rabbitmqUser){
        log.info("【receiveTopic1监听到消息】" + rabbitmqUser.toString());

    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(RabbitmqUser rabbitmqUser) {
        log.info("【receiveTopic2监听到消息】" + rabbitmqUser.toString());
    }
}
