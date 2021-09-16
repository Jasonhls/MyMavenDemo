package com.cn.distributeQueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    public static final String DIRECT_ROUTING_KEY_recvqueue = "recvqueue";
    public static final String DIRECT_ROUTING_KEY_sendqueue = "sendqueue";

    //创建接受消息队列
    @Bean
    public Queue recvqueue(){
        // 第一个参数是队列名字， 第二个参数是指是否持久化
        return new Queue(DIRECT_ROUTING_KEY_recvqueue,true);
    }

    //创建发送消息队列
    @Bean
    public Queue sendqueue(){
        return new Queue(DIRECT_ROUTING_KEY_sendqueue,true);
    }

    //创建交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    //发送消息队列绑定交换机
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(sendqueue()).to(directExchange()).withQueueName();
    }
}
