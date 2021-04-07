package com.cn.threadAndLock.threadDesignMode.orderControl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 12:30
 **/
public class OrderControlByTurnsTest {
    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 5);
        new Thread(() ->{
            wn.print("1", 1, 2);
        }, "t1").start();

        new Thread(() ->{
            wn.print("2", 2, 3);
        }, "t2").start();

        new Thread(() ->{
            wn.print("3", 3, 1);
        }, "t3").start();
    }
}

class WaitNotify {
    //等待标记
    private int flag;
    //循环次数
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    /**
     * @param str 输出内容
     * @param waitFlag 是否等待的标记值
     * @param nextFlag 下一个标记值
     */
    public void print(String str, int waitFlag, int nextFlag) {
        synchronized (this) {
            while(flag != waitFlag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "，打印内容为：" + str);
            flag = nextFlag;
            this.notifyAll();
        }
    }
}

class AwaitSignalTest {

    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
            awaitSignal.print("a", a, b);
        }, "t1").start();
        new Thread(() -> {
            awaitSignal.print("b", b, c);
        }, "t2").start();
        new Thread(() -> {
            awaitSignal.print("c", c, a);
        }, "t3").start();

        Thread.sleep(1000);
        awaitSignal.lock();
        try {
            System.out.println("开始");
            a.signal();
        } finally {
            awaitSignal.unlock();
        }

    }
}

class AwaitSignal extends ReentrantLock {
    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}

class ParkUnParkTest {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        ParkUnPark pup = new ParkUnPark(5);

        t1 = new Thread(() -> {
            pup.print("a", t2);
        }, "t1");
        t2 = new Thread((() -> {
            pup.print("b", t3);
        }), "t2");
        t3 = new Thread(() -> {
            pup.print("c", t1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);

    }
}

class ParkUnPark {
    private int loopNumber;

    public ParkUnPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }
}
