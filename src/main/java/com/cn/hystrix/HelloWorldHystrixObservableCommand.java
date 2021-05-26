package com.cn.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-20 15:39
 **/
public class HelloWorldHystrixObservableCommand extends HystrixObservableCommand<String> {

    private final String name;
    protected HelloWorldHystrixObservableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        System.out.println("in construct! thread: " + Thread.currentThread().getName());
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    System.out.println("in construct! thread: " + Thread.currentThread().getName());
                    if(!observer.isUnsubscribed()) {
//                        observer.onError(getExecutionException());
                        observer.onNext("Hello1" + " thread: " + Thread.currentThread().getName());
                        observer.onNext("Hello2" + " thread: " + Thread.currentThread().getName());
                        observer.onNext(name + " thread: " + Thread.currentThread().getName());
                        System.out.println("complete before-------" + " thread: " + Thread.currentThread().getName());
                        observer.onCompleted();
                        System.out.println("complete after-------" + " thread: " + Thread.currentThread().getName());
                        observer.onCompleted();
                        observer.onNext("abc");
                    }
                }catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }
}
