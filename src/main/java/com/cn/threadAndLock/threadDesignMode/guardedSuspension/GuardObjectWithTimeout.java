package com.cn.threadAndLock.threadDesignMode.guardedSuspension;

public class GuardObjectWithTimeout {
    //结果
    private Object response;

    //获取结果，在一定时间内，超过这个时间，直接返回null
    public Object get(long timeout) {
        synchronized (this) {
            //记录开始时间
            long begin = System.currentTimeMillis();
            //记录经历的时间
            long passedTime = 0;
            //如果没有结果
            while(response == null) {
                //经历的时间超过最大等待时间，退出循环
                if(passedTime >= timeout) {
                    break;
                }
                try {
                    /**
                     * 这里为啥是等待timeout - passedTime，是为了避免虚假唤醒，比如timeout为2秒，
                     * 下面这里设置为this.wait(timeout)，如果只等待了一秒就唤醒了，然后又进行一次循环，第二次循环
                     * 不应该this.wait(2s)，因为之前一次循环已经等待了1s，所以第二次应该只等待1s，
                     * 所以这里的this.wait(long timeout)中的等待时间应该为timeout - passedTime
                     */
                    this.wait(timeout - passedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //求得经历时间
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    //产生结果
    public void complete(Object response) {
        synchronized (this) {
            //给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        GuardObjectWithTimeout guardObject = new GuardObjectWithTimeout();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "begin");
            Object response = guardObject.get(2000);
            System.out.println(Thread.currentThread().getName() + "结果是：" + response);
        }, "t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardObject.complete(new Object());

        }, "t2").start();
    }
}
