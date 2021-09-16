package com.cn.classAndInterface.abstractClass;

import org.junit.Test;

import java.lang.reflect.Modifier;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-25 11:03
 **/
public class AbstractTest {
    /**
     * 判断一个类是否是抽象类
     */
    @Test
    public void judgeAbstractClass() {
        System.out.println(Modifier.isAbstract(MyAbstract.class.getModifiers()));
    }

    /**
     * 判断一个类是否是接口类
     */
    @Test
    public void judgeInterfaceClass() {
        System.out.println(MyInterface.class.isInterface());
    }


    abstract class MyAbstract {

    }

    interface MyInterface {

    }
}
