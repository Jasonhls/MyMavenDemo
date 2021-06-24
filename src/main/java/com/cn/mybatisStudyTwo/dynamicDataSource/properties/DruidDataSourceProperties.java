package com.cn.mybatisStudyTwo.dynamicDataSource.properties;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties extends DataSourceCommonProperties {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Override
    public void determineProperties() {
        if(StringUtils.isBlank(username)) {
            username = dataSourceProperties.determineUsername();
        }
        if(StringUtils.isBlank(password)) {
            password = dataSourceProperties.determinePassword();
        }
        if(StringUtils.isBlank(url)) {
            url = dataSourceProperties.determineUrl();
        }
        if(StringUtils.isBlank(driverClassName)) {
            driverClassName = dataSourceProperties.determineDriverClassName();
        }
    }
}
