package com.cn.mybatisStudyTwo.dynamicDataSource.druidStat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "druid.status")
public class DruidStatViewProperties {
    private boolean enabled;
    private String urlPattern;
    private String allow = "127.0.0.1";
    private String deny;
    private String loginUserName = "root";
    private String loginPassword = "123456";
    private String resetEnable = "false";
}
