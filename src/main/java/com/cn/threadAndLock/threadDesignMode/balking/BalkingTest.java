package com.cn.threadAndLock.threadDesignMode.balking;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 13:53
 **/
public class BalkingTest {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTerminationWithVolatile t = new TwoPhaseTerminationWithVolatile();
        //这里启动了三次，不会重复监控
        t.start();
        t.start();
        t.start();

        Thread.sleep(3500);
        t.stop();
    }
}

class TwoPhaseTerminationWithVolatile {
    //监控线程
    private Thread monitor;
    //停止标记
    private volatile boolean stop = false;
    //判断是否执行过start方法
    private boolean starting = false;

    public void start() {
        //synchronized代码块中的代码越多，上锁时间越长，性能越不好，只需要对需要上锁的代码放到代码块即可
        synchronized (this) {
            if(starting) {
                return;
            }
            starting = true;
        }

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

