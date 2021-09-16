package com.cn.beanlife;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-02 10:51
 **/
public class SubMySpringBean extends MySpringBean implements BeanClassLoaderAware, EnvironmentAware, EmbeddedValueResolverAware,
        ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        System.out.println("子类：属性color的设置");
        this.color = color;
    }

    public SubMySpringBean() {
        System.out.println("子类：调用无参构造函数");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("子类：调用setBeanClassLoader方法");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("子类：调用setEnvironment方法");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        System.out.println("子类：调用setEmbeddedValueResolver方法");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("子类：调用setResourceLoader方法");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("子类：调用setApplicationEventPublisher方法");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("子类：调用setMessageSource方法");
    }

}
