package com.cn.jdk8.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @Author: helisen
 * @Date 2021/9/26 18:23
 * @Description:
 */
public class PublisherDemo {
    public static void main(String[] args) {
        Publisher<String> stringPublisher = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber subscriber) {
                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long l) {
                        System.out.println("执行request方法：" + l);
                    }

                    @Override
                    public void cancel() {
                        System.out.println("执行cancel方法：");
                    }
                });
            }
        };
        stringPublisher.subscribe(new MySubscriber());
    }

    static class MySubscriber implements Subscriber<String> {
        @Override
        public void onSubscribe(Subscription subscription) {
            System.out.println("执行onSubscribe方法：" + subscription);
            subscription.request(100L);
            subscription.cancel();
        }

        @Override
        public void onNext(String s) {
            System.out.println("执行onNext方法：" + s);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("执行onError方法");
        }

        @Override
        public void onComplete() {
            System.out.println("已经完成了。。。。");
        }
    }
}
