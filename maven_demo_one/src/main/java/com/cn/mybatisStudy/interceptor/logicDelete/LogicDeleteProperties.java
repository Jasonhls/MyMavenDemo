package com.cn.mybatisStudy.interceptor.logicDelete;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@ConfigurationProperties(prefix = LogicDeleteProperties.PREFIX)
public class LogicDeleteProperties {
    public static final String PREFIX = "mybatis-plus.global-config.db-config.logic-delete";
    public boolean enabled = false;
    /**
     * 逻辑删除字段
     */
    public String columnName = "is_deleted";
    /**
     * 删除标记值
     */
    public String deleteValue = "1";
    /**
     * 未删除标记值
     */
    public static String noDeleteValue = "0";
    /**
     * 忽略的表
     */
    public Set<String> ignoreTableNames;
}
