package com.cn.rabbitMQ.study.topic;

import com.cn.rabbitMQ.study.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发布与订阅使用的交换机类型为：fanout
 */
public class Producer {
    //交换机名称
    static final String TOPIC_EXCHANGE = "topic_exchange";
    //队列名称
    static final String TOPIC_QUEUE_1 = "topic_queue_1";
    //队列名称
    static final String TOPIC_QUEUE_2 = "topic_queue_2";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 声明交换机：
         * 参数1：交换机名称
         * 参数2：交换机类型：fanout、topic、direct、headers
         */
        channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
        /**
         * 声明（创建）队列
         * 参数一：队列名称
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其他参数
         */
        channel.queueDeclare(TOPIC_QUEUE_1, true, false, false, null);
        channel.queueDeclare(TOPIC_QUEUE_2, true, false, false, null);
        //队列绑定交换机
        channel.queueBind(TOPIC_QUEUE_1, TOPIC_EXCHANGE, "item.#");
        channel.queueBind(TOPIC_QUEUE_2, TOPIC_EXCHANGE, "*.delete");



        String message = "新增了商品，topic模式：routingKey为item.insert";
        channel.basicPublish(TOPIC_EXCHANGE, "item.insert", null, message.getBytes());
        System.out.println("已发送消息：" + message);

        message = "修改了商品，路由模式：routingKey为item.update";
        channel.basicPublish(TOPIC_EXCHANGE, "item.update", null, message.getBytes());
        System.out.println("已发送消息：" + message);

        message = "删除了商品，路由模式：routingKey为item.delete";
        channel.basicPublish(TOPIC_EXCHANGE, "producer.delete", null, message.getBytes());
        System.out.println("已发送消息：" + message);

        message = "其他的消息";
        channel.basicPublish(TOPIC_EXCHANGE, "item.delete.abc", null, message.getBytes());
        System.out.println("已发送消息：" + message);

        //释放资源
        channel.close();
        connection.close();
    }
}
