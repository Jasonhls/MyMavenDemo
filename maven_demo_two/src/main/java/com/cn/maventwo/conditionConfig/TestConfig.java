package com.cn.maventwo.conditionConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Author: 何立森
 * @Date: 2023/04/07/17:34
 * @Description:
 */
@Configuration
public class TestConfig implements EnvironmentAware {
    private Environment env;

    @Value("${seata.enabled}")
    private String seataEnabled;

    static {
        System.out.println("初始化配置文件TestConfig");
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
