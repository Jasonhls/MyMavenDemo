package com.cn.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description:
 * 调用MySpringBean的无参构造，进行初始化
 * 私有属性name的设置
 * 设置beanName
 * 设置 beanFactory
 * 设置applicationContext
 * MySpringBean 调用postProcessBeforeInitialization方法
 * 调用@PostConstruct方法
 * 调用afterPropertiesSet方法
 * 调用xml中的init-method方法
 * MySpringBean 调用postProcessAfterInitialization方法
 * 09:44:50.234 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'a'
 * 调用a的无参构造
 * 09:44:50.235 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'b'
 * 调用b的无参构造
 * MySpringBean的属性name的值：this is my name
 * 09:44:50.249 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@61d47554, started on Thu Jul 02 09:44:49 CST 2020
 * 调用@PreDestroy方法
 * 调用destroy方法
 * 调用xml配置中的destroy-method方法
 * @author: helisen
 * @create: 2020-07-02 08:55
 **/
public class MySpringBean implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    private String name;
    private A a;
    private B b;

    public void setName(String name) {
        System.out.println("私有属性name的设置");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setA(A a) {
        System.out.println("属性注入a");
        this.a = a;
    }

    public MySpringBean() {
        System.out.println("调用MySpringBean的无参构造，进行初始化");
    }

    public MySpringBean(B b) {
        System.out.println("构造器注入b");
        this.b = b;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用destroy方法");
    }

    /**
     * 属性注入后，在postConstruct方法之后执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用afterPropertiesSet方法");
    }

    /**
     * 属性注入后，然后beanPostProcessor的before方法执行后就执行
     */
    @PostConstruct
    public void init() {
        System.out.println("调用@PostConstruct方法");
    }

    /**
     * destroy方法执行之前执行
     */
    @PreDestroy
    public void preDestroy() {
        System.out.println("调用@PreDestroy方法");
    }


    /**
     * 属性注入后开始依次执行setBeanName，setBeanFactory，setApplicationContext
     * 最先执行setBeanName，其次执行setBeanFactory，最后执行setApplicationContext
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("设置 beanFactory");
    }
    @Override
    public void setBeanName(String s) {
        System.out.println("设置beanName");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("设置applicationContext");
    }

    /**
     * xml中的初始化方法，在afterPropertiesSet方法之后执行，在beanPostProcessor的after方法之前执行
     */
    public void initMethod() {
        System.out.println("调用xml中的init-method方法");
    }

    /**
     * xml中销毁方法，在destroy方法后执行
     */
    public void destroyMethod() {
        System.out.println("调用xml配置中的destroy-method方法");
    }
}
