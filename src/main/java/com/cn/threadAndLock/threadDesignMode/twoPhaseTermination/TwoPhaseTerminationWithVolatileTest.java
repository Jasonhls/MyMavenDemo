package com.cn.threadAndLock.threadDesignMode.twoPhaseTermination;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 13:33
 **/
public class TwoPhaseTerminationWithVolatileTest {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTerminationWithVolatile t = new TwoPhaseTerminationWithVolatile();
        //这里如果多次执行t.start()，会创建多个monitor监控线程，为了避免这个，可以使用Balking模式，具体参考Balking模式
        t.start();

        Thread.sleep(3500);
        t.stop();
    }
}

class TwoPhaseTerminationWithVolatile {
    //监控线程
    private Thread monitor;
    private volatile boolean stop = false;

    public void start() {
        monitor = new Thread(() -> {
            while(true) {
                Thread current = Thread.currentThread();
                if(stop) {
                    System.out.println(current.getName() + "，料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println(current.getName() + "，执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();//打印报错日志，没有这行，就不会打印报错日志
                }
            }
        }, "t1");

        monitor.start();
    }

    public void stop() {
        stop = true;
        //stop标志变成true的时候，monitor线程可能还在sleep，为了monitor线程尽快执行下一轮循环，就需要让monitor跳出睡眠状态，
        // 可以使用interrupt()方法打断monitor线程的睡眠
        monitor.interrupt();
    }
}
