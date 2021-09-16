package com.cn.exception.utils;

import org.springframework.boot.SpringApplication;

import java.util.Set;

public class SpringApplicationUtils {
    public static boolean isBootApplication(SpringApplication application){
        if(application == null){
            return false;
        }
        Set<Object> allSources = application.getAllSources();
        if(allSources.size() > 1){
            return false;
        }
        return allSources.contains(application.getMainApplicationClass());
    }
}
