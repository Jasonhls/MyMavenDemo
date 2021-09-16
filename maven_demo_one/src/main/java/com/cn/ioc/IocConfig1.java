package com.cn.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-01 11:20
 **/
@Configuration
@ComponentScan(basePackages = {"com.cn.ioc"})
@Import(value = MyImportBeanDefinitionRegistrar.class)
public class IocConfig1 {
    /**
     * 注入Student类到spring中，beanName的名称为s（默认取方法的名称），class为Student.class
     * @return
     */
    /*@Bean
    public Student Ss() {
        return new Student();
    }*/
}
