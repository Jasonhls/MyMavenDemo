package com.cn.rabbitMQ.study2.config;

import com.cn.rabbitMQ.study2.RabbitmqOrder;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Jason on 2019/3/28.
 */
@Component
@Slf4j
public class DelayReceiver {
    @RabbitListener(queues = {DelayRabbitConfig.ORDER_QUEUE_NAME})
    public void orderDelayQueue(RabbitmqOrder rabbitmqOrder, Message message, Channel channel) {
        log.info("###########################################");
        log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]",  new Date(), rabbitmqOrder.toString());
        if(rabbitmqOrder.getOrderStatus() == 0) {
            rabbitmqOrder.setOrderStatus(2);
            log.info("【该订单未支付，取消订单】" + rabbitmqOrder.toString());
        } else if(rabbitmqOrder.getOrderStatus() == 1) {
            log.info("【该订单已完成支付】");
        } else if(rabbitmqOrder.getOrderStatus() == 2) {
            log.info("【该订单已取消】");
        }
        log.info("###########################################");
    }
}
