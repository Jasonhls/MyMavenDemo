package com.cn.mybatisStudyTwo.dynamicDataSource.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnDynamicDataSourceCondition implements Condition {
    /**
     * @param conditionContext  spring的上下文对象
     * @param annotatedTypeMetadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment env = conditionContext.getEnvironment();
        String slaveEnabled = env.getProperty("spring.datasource.slaveEnabled");
        String slave_enabled = env.getProperty("spring.datasource.slave-enabled");
        return "true".equals(slaveEnabled) || "true".equals(slave_enabled);
    }
}
