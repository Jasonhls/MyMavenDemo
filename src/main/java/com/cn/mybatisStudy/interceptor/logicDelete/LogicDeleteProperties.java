package com.cn.mybatisStudy.interceptor.logicDelete;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@ConfigurationProperties(prefix = LogicDeleteProperties.PREFIX)
public class LogicDeleteProperties {
    public static final String PREFIX = "mybatis-plus.global-config.db-config.logic-delete";
    private boolean enabled = false;
    /**
     * 逻辑删除字段
     */
    private String column = "is_deleted";
    /**
     * 删除标记值
     */
    private String deleteValue = "1";
    /**
     * 未删除标记值
     */
    private String noDeleteValue = "0";
    /**
     * 忽略的表
     */
    private Set<String> ignoreTableNames;
}
