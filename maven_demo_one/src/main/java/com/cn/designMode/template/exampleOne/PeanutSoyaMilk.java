package com.cn.designMode.template.exampleOne;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 11:25
 * 花生豆浆
 **/
public class PeanutSoyaMilk extends SoyaMilk{
    @Override
    void addCondiments() {
        System.out.println("加入上好的花生");
    }
}
