package com.cn.rabbitMQ.study.routing;

import com.cn.rabbitMQ.study.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发布与订阅使用的交换机类型为：fanout
 */
public class Producer {
    //交换机名称
    static final String DIRECT_EXCHANGE = "direct_exchange";
    //队列名称
    static final String DIRECT_QUEUE_INSERT = "direct_queue_insert";
    //队列名称
    static final String DIRECT_QUEUE_UPDATE = "direct_queue_update";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 声明交换机：
         * 参数1：交换机名称
         * 参数2：交换机类型：fanout、topic、direct、headers
         */
        channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        /**
         * 声明（创建）队列
         * 参数一：队列名称
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其他参数
         */
        channel.queueDeclare(DIRECT_QUEUE_INSERT, true, false, false, null);
        channel.queueDeclare(DIRECT_QUEUE_UPDATE, true, false, false, null);

        //队列绑定交换机
        channel.queueBind(DIRECT_QUEUE_INSERT, DIRECT_EXCHANGE, "insert");
        channel.queueBind(DIRECT_QUEUE_UPDATE, DIRECT_EXCHANGE, "update");

        String message = "新增了商品，路由模式：routingKey为insert";
        /**
         * 参数1：交换机名称，如果没有指定则使用默认Default Exchange
         * 参数2：路由key，简单模式可以传递队列名称
         * 参数3：消息其他属性
         * 参数4：消息内容
         */
        channel.basicPublish(DIRECT_EXCHANGE, "insert", null, message.getBytes());
        System.out.println("已发送消息：" + message);

        message = "修改了商品，路由模式：routingKey为update";
        channel.basicPublish(DIRECT_EXCHANGE, "update", null, message.getBytes());
        System.out.println("已发送消息：" + message);
        //释放资源
        channel.close();
        connection.close();
    }
}
