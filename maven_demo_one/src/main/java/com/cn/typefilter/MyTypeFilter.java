package com.cn.typefilter;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        /** 排除rabbitmq相关的包，以及springBatch相关的包，以及mybatis相关的包 */
        if(className.contains("com.cn.distributeQueue") || className.contains("com.cn.rabbitMQ")
                || className.contains("com.cn.springBatch") || className.contains("com.cn.mybatisStudy")) {
            return true;
        }
        return false;
    }
}
