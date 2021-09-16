package com.cn.rabbitMQ.study2.config;

import com.cn.rabbitMQ.study2.RabbitmqUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Jason on 2019/3/28.
 */
@Component
@Slf4j
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue(){
        RabbitmqUser rabbitmqUser = new RabbitmqUser();
        rabbitmqUser.setUserId("123456");
        rabbitmqUser.setName("lizhencheng");
        log.info("【sendDirectQueue已发送消息】");
        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, rabbitmqUser);
    }

    public void sendTopic(){
        RabbitmqUser rabbitmqUser1 = new RabbitmqUser();
        rabbitmqUser1.setUserId("123456");
        rabbitmqUser1.setName("lizhencheng");

        RabbitmqUser rabbitmqUser2 = new RabbitmqUser();
        rabbitmqUser2.setUserId("456789");
        rabbitmqUser2.setName("张三");

        log.info("【sendTopic已发送消息】");

        //第一个参数：TopicExchange名子
        //第二个参数：Route-Key
        //第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE,"hls.message", rabbitmqUser1);
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE,"hls.hls", rabbitmqUser2);
    }

    public void sendFanout(){
        RabbitmqUser rabbitmqUser = new RabbitmqUser();
        rabbitmqUser.setUserId("123456");
        rabbitmqUser.setName("lizhencheng");

        log.info("【sendFanout已发送消息】");
        //注意，这里的第二个参数为空
        //因为fanout交换器不处理路由键，只是的将队列绑定到交换机上，
        //每个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上
        this.amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE,"", rabbitmqUser);
    }
}
