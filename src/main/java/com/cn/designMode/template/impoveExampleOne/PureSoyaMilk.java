package com.cn.designMode.template.impoveExampleOne;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 11:39
 **/
public class PureSoyaMilk extends SoyaMilk{
    @Override
    void addCondiments() {
        //空的，这里什么也添加
    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
