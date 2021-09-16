package com.cn.ioc;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-01 12:43
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(Person.class);
        //autowireMode决定其他类注入到Person类中的方式
        beanDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_NO);
        beanDefinitionRegistry.registerBeanDefinition("person", beanDefinition);
    }
}
