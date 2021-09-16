package com.cn.rabbitMQ.study.worker;

import com.cn.rabbitMQ.study.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Producer.QUEUE_NAME, true, false, false, null);
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

        /**
         * 监听消息：
         * 参数一：队列名称
         * 参数二：是否自动确认，设置为true表示消息接受到自动想mq回复收到了，mq接受到回复后会删除消息；设置为false则需要手动确认
         */
        channel.basicConsume(Producer.QUEUE_NAME, true, consumer);

        //不关闭资源，应该一直监听消息
//        channel.close();
//        connection.close();
    }
}
