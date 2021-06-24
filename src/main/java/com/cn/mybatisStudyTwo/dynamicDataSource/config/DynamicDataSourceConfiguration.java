package com.cn.mybatisStudyTwo.dynamicDataSource.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.cn.mybatisStudyTwo.dynamicDataSource.DynamicDataSource;
import com.cn.mybatisStudyTwo.dynamicDataSource.DynamicDataSourceHolder;
import com.cn.mybatisStudyTwo.dynamicDataSource.condition.OnDynamicDataSourceCondition;
import com.cn.mybatisStudyTwo.dynamicDataSource.properties.DataSourceCommonProperties;
import com.cn.mybatisStudyTwo.dynamicDataSource.properties.DruidDataSourceProperties;
import com.cn.mybatisStudyTwo.dynamicDataSource.properties.SlaveDruidDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Conditional(value = OnDynamicDataSourceCondition.class)
@EnableConfigurationProperties(value = {DruidDataSourceProperties.class, SlaveDruidDataSourceProperties.class})
public class DynamicDataSourceConfiguration {

    /**
     * 返回动态数据库对象
     * @param properties
     * @param slaveProperties
     * @param filters
     * @return
     */
    @Bean
    public DataSource dataSource(DruidDataSourceProperties properties,
                                 SlaveDruidDataSourceProperties slaveProperties,
                                 List<Filter> filters) {
        //创建动态数据库对象
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        //创建主数据库
        DruidDataSource defaultDataSource = new DruidDataSource();
        properties.config(defaultDataSource);
        defaultDataSource.setProxyFilters(filters);
        log.info("装载primary dataSource .... {}", defaultDataSource.getUrl());

        List<DataSourceCommonProperties> salvesPropertiesList = slaveProperties.getSalvesProperties();
        //从数据库集合
        Map<Object, Object> slaveDataSourceMap = new HashMap<>(salvesPropertiesList.size());
        slaveDataSourceMap.put("primary", defaultDataSource);
        DynamicDataSourceHolder.slaveDataSourceNames.add("primary");

        for (DataSourceCommonProperties salveDataSourceProperties : salvesPropertiesList) {
            //创建从数据库
            DruidDataSource slaveDruidDataSource = new DruidDataSource();
            slaveProperties.config(slaveDruidDataSource, salveDataSourceProperties);
            slaveDataSourceMap.put(salveDataSourceProperties.getDataSourceName(), slaveDruidDataSource);

            DynamicDataSourceHolder.slaveDataSourceNames.add(salveDataSourceProperties.getDataSourceName());

            log.info("装载slave dataSource .... {}", slaveDruidDataSource.getUrl());
        }

        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        dynamicDataSource.setTargetDataSources(slaveDataSourceMap);

        return dynamicDataSource;
    }
}
