package com.cn.distributeQueue;

import com.cn.pojo.User;
import com.cn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageProducer messageProducer;

    /**
     * 监听方法传入的参数需要和消息生产者的一致
     * @param message
     */
    @RabbitListener(queues = RabbitmqConfig.DIRECT_ROUTING_KEY_recvqueue)
    @RabbitHandler//handler注解来指定对消息的处理
    public void onMessage(Message message){
        log.info("consumer receive message---------->:{}",message);
        User user = userService.selectUserByUserName(new String(message.getBody()));
        messageProducer.sendMessage(user);
    }

    /**
     * 业务消费
     * @param message
     */
    @RabbitListener(queues = RabbitmqConfig.DIRECT_ROUTING_KEY_sendqueue)
    @RabbitHandler
    public void basicMessage(Object message){
        log.info("[x] Received'" + message + "'");
    }
}
