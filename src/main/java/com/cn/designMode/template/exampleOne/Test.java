package com.cn.designMode.template.exampleOne;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 10:22
 **/
public class Test {

    @org.junit.Test
    public void test() {
        System.out.println("-------制作红豆豆浆---------");
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        System.out.println("--------制作花生豆浆--------");

        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();
    }
}
