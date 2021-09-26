package com.cn.jdk8.consumer;

import java.util.function.Consumer;

/**
 * @Author: helisen
 * @Date 2021/9/26 18:02
 * @Description:
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        method("哈哈", (String name) -> {
//            System.out.println(name);
            System.out.println(new StringBuilder(name).reverse());
        });

        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("第一步骤：接受到的参数为 " + s);
            }
        };
        Consumer<String> consumer2 = consumer1.andThen(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("第二步骤：计算");
            }
        });

        consumer2.accept("你好吗？");
    }

    public static void method(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }


}
