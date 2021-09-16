package com.cn.mybatisStudyTwo.dynamicDataSource.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class SlaveDruidDataSourceProperties extends DataSourceCommonProperties {
    /**
     * 是否启用从数据库，通过配置设置其值
     */
    private String slaveEnabled;
    /**
     * 从数据库配置集合，通过配置设置其值
     */
    private List<DataSourceCommonProperties> salvesProperties;


    @Override
    public void determineProperties() {

    }
}
