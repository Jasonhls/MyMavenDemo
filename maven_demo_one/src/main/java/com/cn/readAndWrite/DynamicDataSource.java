package com.cn.readAndWrite;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    //写数据源
    private Object writeDataSource;
    //读数据源
    private Object readDataSource;

    @Override
    public void afterPropertiesSet() {
        if(this.writeDataSource == null){
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        //覆盖AbstractRoutingDataSource中默认的数据源为write
        setDefaultTargetDataSource(writeDataSource);
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(),writeDataSource);
        if(readDataSource != null){
            //如果读数据源不为空就压入map
            targetDataSources.put(DynamicDataSourceGlobal.READ.name(),readDataSource);
        }
        //设置目标数据源为map中压入的数据源
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 获取与数据源相关的key，此key是Map<String,DataSource> resolvedDataSources中与数据源绑定的key值
     * 在通过determineTargetDataSource获取目标数据源时使用
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceGlobal dataSource = DynamicDataSourceHolder.getDataSource();
        if(dataSource == null || dataSource == DynamicDataSourceGlobal.WRITE){
            return DynamicDataSourceGlobal.WRITE.name();
        }
        return DynamicDataSourceGlobal.READ.name();
    }

    public Object getWriteDataSource() {
        return writeDataSource;
    }

    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    public Object getReadDataSource() {
        return readDataSource;
    }

    public void setReadDataSource(Object readDataSource) {
        this.readDataSource = readDataSource;
    }
}
