package com.cn.threadAndLock.threadCorrespond;

/**
 * Created by Jason on 2019/3/4.
 */
public class Printer{
    int flag = 1;
    public synchronized void printer1() throws Exception{
        if(flag != 1){
            this.wait();
        }
        System.out.print("何");
        System.out.print("立");
        System.out.print("森");
        System.out.print("吃");
        System.out.println("饭");
        flag = 2;
        this.notify();
    }

    public synchronized void printer2() throws InterruptedException {
        if(flag != 2){
            this.wait();
        }
        System.out.print("电");
        System.out.print("脑");
        System.out.print("坏");
        System.out.println("了");
        flag = 1;
        this.notify();
    }
}
