package com.cn.exception.config;

import com.cn.exception.CachingMetadataReaderFactoryProvider;
import com.cn.exception.CodeMsgEnumProcessor;
import com.cn.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration(GlobalExceptionHandler.class)
public class ExceptionConfiguration {
    @Bean
    public static CodeMsgEnumProcessor codeMsgEnumProcessor(CachingMetadataReaderFactoryProvider metadataReaderFactoryProvider){
        return new CodeMsgEnumProcessor(metadataReaderFactoryProvider);
    }
}
