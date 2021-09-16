package com.cn.mybatisStudy.interceptor.logicDelete;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiIgnoreLogicDelete {
    String[] value() default {};
}
