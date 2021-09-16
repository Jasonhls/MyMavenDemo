package com.cn.mybatisStudyTwo.dynamicDataSource.config;

import com.cn.mybatisStudyTwo.dynamicDataSource.druidStat.DruidStatViewServletConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Configuration
@Import(value = {SystemConfiguration.class, DynamicDataSourceConfiguration.class, DruidStatViewServletConfiguration.class})
public class DbBootstrapConfiguration {
}
