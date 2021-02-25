package com.cn.threadAndLock.threadCorrespond;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 2019/3/5.
 */
public class Printer3 {
    private ReentrantLock t = new ReentrantLock();
    private Condition c1 = t.newCondition();
    private Condition c2 = t.newCondition();
    private Condition c3 = t.newCondition();
    private int flag = 1;
    public void printer1() throws InterruptedException {
        t.lock();
        if(flag != 1){
            c1.await();
        }
        System.out.print("今");
        System.out.print("天");
        System.out.print("天");
        System.out.print("气");
        System.out.print("真");
        System.out.print("不");
        System.out.println("错");
        flag = 2;
        c2.signal();
        t.unlock();
    }

    public void printer2() throws InterruptedException {
        t.lock();
        if(flag != 2){
            c2.await();
        }
        System.out.print("咱");
        System.out.print("们");
        System.out.print("出");
        System.out.print("去");
        System.out.println("吧");
        flag = 3;
        c3.signal();
        t.unlock();
    }

    public void printer3() throws InterruptedException {
        t.lock();
        if(flag != 3){
            c3.await();
        }
        System.out.print("可");
        System.out.print("以");
        System.out.println("呀");
        flag = 1;
        c1.signal();
        t.unlock();
    }
}
