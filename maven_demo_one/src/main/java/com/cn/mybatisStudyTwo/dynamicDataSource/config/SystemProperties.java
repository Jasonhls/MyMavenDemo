package com.cn.mybatisStudyTwo.dynamicDataSource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "foco")
public class SystemProperties {
    /**
     * 是否开启读写分离 默认不开启
     */
    private Boolean readWriteSpilt = false;
    /**
     * 走从库读的方法
     */
    private List<String> readMethod;
}
