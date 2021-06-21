package com.cn.mybatisStudy.config;

import com.cn.mybatisStudy.interceptor.security.SafeSqlProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {SafeSqlProperties.class})
public class MybatisConfig {
}
