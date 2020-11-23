package com.cn.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-02 09:17
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MySpringBean) {
            System.out.println("MySpringBean 调用postProcessBeforeInitialization方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MySpringBean) {
            System.out.println("MySpringBean 调用postProcessAfterInitialization方法");
        }
        return bean;
    }
}
