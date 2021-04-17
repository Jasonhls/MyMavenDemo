package com.cn.rabbitMQ.study;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    public static Connection getConnection() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        //虚拟主机名称：默认为 /，这里必须先去界面上创建 /itcast这个虚拟主机名称
        connectionFactory.setVirtualHost("/itcast");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //创建连接
        return connectionFactory.newConnection();
    }
}
