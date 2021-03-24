package com.cn.annotation;

import org.junit.Test;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-18 10:13
 **/
public class AnnotationTest {
    /**
     * 测试被注解@SpringBootApplication标注的类肯定被注解@Component所标注
     */
    @Test
    public void test() {
        Class<MySpringBootApplication> clazz = MySpringBootApplication.class;
        StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(clazz, true);
        System.out.println(metadata.isAnnotated(Component.class.getName()));
    }
}
