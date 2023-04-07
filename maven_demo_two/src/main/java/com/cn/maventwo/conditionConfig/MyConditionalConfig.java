package com.cn.maventwo.conditionConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 何立森
 * @Date: 2023/04/07/17:33
 * @Description:
 * seata.enableAutoDataSourceProxy:true，这样写，默认值就是true，
 * 从org.springframework.util.PropertyPlaceholderHelper#parseStringValue方法中有如下代码执行：
 * String defaultValue = placeholder.substring(separatorIndex + this.valueSeparator.length());
 */
@ConditionalOnExpression("${condition.enabled:true} && ${condition.enableAutoDataSourceProxy:true} && ${condition.enable-auto-data-source-proxy:true}")
@Configuration
public class MyConditionalConfig {
    static {
        System.out.println("初始化走到这里了");
    }
}
