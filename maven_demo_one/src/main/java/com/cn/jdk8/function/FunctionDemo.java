package com.cn.jdk8.function;

import java.util.function.Function;

/**
 * @Author: helisen
 * @Date 2021/9/26 17:14
 * @Description:
 */
public class FunctionDemo {
    public static void main(String[] args) {
        //定义一个函数，Function<T, R>，其中T为入参，R为出参
        Function<Integer, String> function = new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) {
                return "result=" + integer * 3;
            }
        };

        System.out.println(function.apply(29));

        //两个函数串行执行
        Function<Integer, String> function1 = function.andThen(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "the answer is : " + s;
            }
        });

        System.out.println(function1.apply(35));


        //用lambda实现如下：
        Function<Integer, String> lambdaFunction = o -> "result=" + o * 3;
        System.out.println(lambdaFunction.apply(29));

        Function<Integer, String> lambdaFunction1 = lambdaFunction.andThen(o -> "the answer is : " + o);
        System.out.println(lambdaFunction1.apply(35));
    }
}
