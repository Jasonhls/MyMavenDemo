package com.cn.mybatisStudyTwo.dynamicDataSource;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    //从数据库dataSourceName集合
    public static List<String> slaveDataSourceNames = new ArrayList<>();

    //获取数据源
    public static String getDataSource() {
        return dataSourceHolder.get();
    }

    //设置数据源
    public static void setDataSource(String dataSource) {
        dataSourceHolder.set(dataSource);
    }

    //清空数据源
    public static void clearDataSource() {
        dataSourceHolder.remove();
    }

}
