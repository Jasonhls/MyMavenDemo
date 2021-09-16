package com.cn.mybatisStudyTwo.dynamicDataSource.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cn.mybatisStudyTwo.dynamicDataSource.fieldAutoFill.FieldAutoFillProperties;
import com.cn.mybatisStudyTwo.dynamicDataSource.fieldAutoFill.FieldFillMetaObjectHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = {SystemProperties.class, FieldAutoFillProperties.class})
public class SystemConfiguration {


    /**
     * 注入字段填充的MetaObjectHandler
     * @return
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new FieldFillMetaObjectHandler();
    }

}
