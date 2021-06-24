package com.cn.mybatisStudyTwo.dynamicDataSource.annotation;

import java.lang.annotation.*;

/**
 * 用于指定数据源
 */
@Documented
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    /** 指定数据源的dataSourceName*/
    String value();
}
