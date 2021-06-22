package com.cn.mybatisStudy.interceptor.logicDelete;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreLogicDelete {
}
