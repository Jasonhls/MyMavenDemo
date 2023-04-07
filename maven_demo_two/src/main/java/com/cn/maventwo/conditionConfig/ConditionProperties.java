package com.cn.maventwo.conditionConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 何立森
 * @Date: 2023/04/07/17:35
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "condition")
@Data
public class ConditionProperties {
    private boolean enabled = true;
    private boolean enableAutoDataSourceProxy = true;
}
