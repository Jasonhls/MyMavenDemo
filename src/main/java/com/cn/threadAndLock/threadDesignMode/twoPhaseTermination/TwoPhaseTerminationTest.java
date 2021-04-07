package com.cn.threadAndLock.threadDesignMode.twoPhaseTermination;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 11:11
 **/
public class TwoPhaseTerminationTest {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        //注线程睡了3.5秒之后，让tpt线程优雅的停止
        Thread.sleep(3500);
        tpt.stop();
    }
}

class TwoPhaseTermination {
    //监控线程
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while(true) {
                Thread current = Thread.currentThread();
                if(current.isInterrupted()) {
                    System.out.println(current.getName() + "，料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);//情况1：如果这里被打断，会抛出异常，线程的打断标识被清空，为false
                    System.out.println(current.getName() + "，执行监控记录");//情况2：如果在这里被打断，那么线程的打断标识变成true，走上面逻辑会退出循环
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //情况1那里被打断，打断标识还是false，没法退出循环，因此这里需要重新设置打断标识为true
                    current.interrupt();
                }
            }
        }, "t1");

        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
