package com.cn.controller;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

@RocketMQMessageListener(topic = "test-hls",consumerGroup = "consumer-group-hls")
public class RocketmqConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {

    }
}
