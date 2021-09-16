package com.cn.rabbitMQ.study2.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jason on 2019/3/25.
 */
@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "direct_queue";

    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topic.exchange";

    public static final String FANOUT_EXCHANGE = "fanout.exchange";

    /**
     * direct模式
     * @return
     */
    @Bean
    public Queue directQueue(){
        // 第一个参数是队列名字， 第二个参数是指是否持久化
        return new Queue(QUEUE,true);
    }

    /**
     * topic模式
     * @return
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1);
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("hls.message");
    }
    @Bean
    public Binding topicBining2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("hls.#");
    }

    /**
     * Fanout模式
     * Fanout就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }
}
