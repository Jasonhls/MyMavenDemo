package com.cn.threadAndLock.threadDesignMode;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 15:51
 **/
public class Singleton {
    private Singleton () { }

    private static Singleton INSTANCE = null;

    /**
     * 每次调用都要获取锁，性能不好
     * @return
     */
    public static Singleton getInstance() {
        synchronized (Singleton.class) {
            if(INSTANCE == null) {
                INSTANCE = new Singleton();
            }
            return INSTANCE;
        }
    }
}

/**
 * 第一次调用该方法的时候才会去获取锁
 * 但是下面这种可能会发生指令重排
 */
class Singleton2 {
    private Singleton2 () { }

    private static Singleton2 INSTANCE = null;

    public static Singleton2 getInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton2.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }
}

/**
 * INSTANCE变量上添加volatile修饰符，防止指令重排
 */
class Singleton3 {
    private Singleton3 () { }

    private static volatile Singleton3 INSTANCE = null;

    public static Singleton3 getInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton3.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
