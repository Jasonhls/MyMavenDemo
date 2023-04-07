package com.cn.maventwo.autoConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 何立森
 * @Date: 2023/04/07/18:07
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "remind")
@Data
public class AutoRemindConfig {
    private boolean enabled;
    private String name;
    private Integer age;
}
