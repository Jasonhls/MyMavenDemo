package com.cn.rabbitMQ.study.simple;

import com.cn.rabbitMQ.study.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    public  static String QUEUE_NAME = "simple_queue";
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明创建队列
        /**
         * 参数一：队列名称
         * 参数二：是否定义持久化队列
         * 参数三：是否独占本次连接
         * 参数四：是否在不使用的时候自动删除队列
         * 参数五：队列其他参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //要发送的消息
        String message = "哈哈哈哈，你好";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("已发送消息：" + message);
        //释放资源
        channel.close();
        connection.close();
    }
}
