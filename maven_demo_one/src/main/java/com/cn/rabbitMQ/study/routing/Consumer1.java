package com.cn.rabbitMQ.study.routing;

import com.cn.rabbitMQ.study.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

import static com.cn.rabbitMQ.study.routing.Producer.DIRECT_EXCHANGE;
import static com.cn.rabbitMQ.study.routing.Producer.DIRECT_QUEUE_INSERT;

public class Consumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        //声明队列
        channel.queueDeclare(DIRECT_QUEUE_INSERT, true, false, false, null);
        //队列绑定交换机
        channel.queueBind(DIRECT_QUEUE_INSERT, DIRECT_EXCHANGE, "insert");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("路由key为：" + envelope.getRoutingKey());
                System.out.println("交换机为：" + envelope.getExchange());
                System.out.println("消息id为：" + envelope.getDeliveryTag());
                System.out.println("接受到的消息：" + new String(body, "utf-8"));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        channel.basicConsume(DIRECT_QUEUE_INSERT, true, consumer);
    }
}
