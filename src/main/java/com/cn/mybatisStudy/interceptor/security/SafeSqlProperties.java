package com.cn.mybatisStudy.interceptor.security;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@ConfigurationProperties(prefix = SafeSqlProperties.PREFIX)
public class SafeSqlProperties {

    public final static String PREFIX = "mybatis-plus.global-config.db-config.sql-safe";

    /**
     * 启用安全SQL拦截
     */
    private boolean enabled = true;
    /**
     * 启用安全删除
     */
    private boolean enableSafeDelete = true;
    /**
     * 启用安全更新
     */
    private boolean enableSafeUpdate = true;
    /**
     * 安全删除校验忽略的表
     */
    private Set<String> safeDeleteIgnoreTable = new HashSet<>();
    /**
     * 安全更新校验忽略的表
     */
    private Set<String> safeUpdateIgnoreTable = new HashSet<>();

}
