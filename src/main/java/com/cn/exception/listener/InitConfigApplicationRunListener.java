package com.cn.exception.listener;

import com.cn.exception.utils.PropertySourceUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class InitConfigApplicationRunListener extends BootApplicationRunListenerAdapter{
    public InitConfigApplicationRunListener(SpringApplication application, String[] args){
        super(application,args);
    }

    @Override
    protected void doStarting() {
        Class<?> mainApplicationClass = getApplication().getMainApplicationClass();
        ComponentScan annotation = mainApplicationClass.getAnnotation(ComponentScan.class);
        Set<String> packageSet = new LinkedHashSet<>();
        packageSet.add(mainApplicationClass.getPackage().getName());
        if(annotation != null){
            if(annotation.value().length != 0){
                for (String s : annotation.value()){
                    if(StringUtils.hasText(s)){
                        packageSet.add(s);
                    }
                }
            }
            if(annotation.basePackages().length != 0){
                for (String s : annotation.basePackages()){
                    if(StringUtils.hasText(s)){
                        packageSet.add(s);
                    }
                }
            }
            if(annotation.basePackageClasses().length != 0){
                for (Class<?> aclass : annotation.basePackageClasses()){
                    packageSet.add(aclass.getPackage().getName());
                }
            }
        }
        PropertySourceUtils.setBasePackages(new LinkedList<>(packageSet));
    }
}
