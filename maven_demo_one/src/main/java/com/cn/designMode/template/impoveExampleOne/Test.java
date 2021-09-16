package com.cn.designMode.template.impoveExampleOne;


/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 11:42
 **/
public class Test {

    @org.junit.Test
    public void testImpove() {
        System.out.println("-------制作红豆豆浆---------");
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        System.out.println("--------制作花生豆浆--------");

        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();

        System.out.println("--------制作纯豆浆--------");
        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
