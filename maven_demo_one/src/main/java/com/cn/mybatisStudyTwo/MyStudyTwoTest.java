package com.cn.mybatisStudyTwo;

import com.cn.mybatisStudyTwo.config.MybatisConfigTwo;
import com.cn.mybatisStudyTwo.mapper.MyStudyMapperTwo;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyStudyTwoTest {
    /**
     * 采用全配置的方式
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfigTwo.class);
        MyStudyMapperTwo bean = context.getBean(MyStudyMapperTwo.class);
        System.out.println(bean.getUserById(1L));
    }
}
