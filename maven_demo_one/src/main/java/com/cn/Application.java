package com.cn;

import com.cn.distributeQueue.RabbitmqConfig;
import com.cn.rabbitMQ.study2.config.DelayRabbitConfig;
import com.cn.rabbitMQ.study2.config.RabbitMQConfig;
import com.cn.typefilter.MyTypeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @description: 测试
 * @author: wangqiang
 * @create: 2020-04-21 20:17
 **/
@SpringBootApplication
/** 过滤掉rabbitMQ相关的类 */
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {DelayRabbitConfig.class, RabbitMQConfig.class, RabbitmqConfig.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class}),
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
