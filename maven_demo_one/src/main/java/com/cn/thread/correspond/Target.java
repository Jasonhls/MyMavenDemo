package com.cn.thread.correspond;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-24 11:38
 **/
public class Target {
    /**
     * synchronized定义在类上，默认是以当前对象为锁
     */
    synchronized public void methodA() {
        System.out.println("hello methodA");
    }

    synchronized public void methodB() {
        System.out.println("hello methodB");
    }

    static class A extends Thread {
        Target target;

        public A(Target target) {
            this.target = target;
        }

        @Override
        public void run() {
            target.methodA();
        }
    }

    static class B extends Thread {
        Target target;

        public B(Target target) {
            this.target = target;
        }

        @Override
        public void run() {
            target.methodB();
        }
    }

    /**
     * 本质上就是"共享内存"式的通信。A、B两个线程需要访问同一个共享变量target，谁先拿到了锁，谁就先执行，而另一个线程需要等待。
     * 这样，线程A和线程B就实现了通信。
     */
    public static void main(String[] args) {
        Target target = new Target();
        A a = new A(target);
        B b = new B(target);

        a.start();
        b.start();
    }
}
