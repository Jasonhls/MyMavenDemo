package com.cn.hystrix;

import rx.Observable;
import rx.Subscriber;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-20 15:37
 **/
public class HystrixTest {
    public static void main(String[] args) {
        String test = new HelloWorldHystrixCommand("test").execute();
        System.out.println(test);
    }
    /*public static void main(String[] args) {
        Observable<String> observable = new HelloWorldHystrixObservableCommand("test").observe();
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("-----completed------");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("-----error-----" + throwable);
            }

            @Override
            public void onNext(String s) {
                System.out.println("------next-------" + s);
            }
        });
    }*/
}
