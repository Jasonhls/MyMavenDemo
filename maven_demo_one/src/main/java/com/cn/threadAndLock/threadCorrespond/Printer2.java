package com.cn.threadAndLock.threadCorrespond;

/**
 * Created by Jason on 2019/3/4.
 */
public class Printer2 {
    private int flag = 1;

    public void printer1() throws InterruptedException {
        synchronized (this){
            if(flag == 1){
                this.wait();
            }
            System.out.print("何");
            System.out.print("立");
            System.out.print("森");
            System.out.print("吃");
            System.out.println("饭");
            flag = 2;
            this.notifyAll();
        }
    }

    public void printer2() throws InterruptedException {
        synchronized (this){
            if(flag == 2){
                this.wait();
            }
            System.out.print("今");
            System.out.print("天");
            System.out.print("下");
            System.out.print("雨");
            System.out.println("了");
            flag = 3;
            this.notifyAll();
        }
    }

    public void printer3() throws InterruptedException {
        synchronized (this){
            if(flag == 3){
                this.wait();
            }
            System.out.print("带");
            System.out.print("伞");
            System.out.println("了");
            flag = 1;
            this.notifyAll();
        }
    }
}
