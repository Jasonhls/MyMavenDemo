package com.cn.mybatisStudyTwo.dynamicDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 注入动态的数据源对象，通过getConnection()来获取连接，从而与数据库进行交互，其中AbstractRoutingDataSource的getConnection()方法会调用
 * determineTargetDataSource()方法，该方法又会通过调用determineCurrentLookupKey()方法决定当前数据源是哪一个。
 *  在AbstractRoutingDataSource的afterProperties()方法中，会把targetDataSources添加到resolvedDataSources中，
 *  如果DefaultTargetDataSource不为空，则会赋值给resolvedDefaultDataSource。
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
