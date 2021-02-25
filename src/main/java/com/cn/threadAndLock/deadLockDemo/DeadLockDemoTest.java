package com.cn.threadAndLock.deadLockDemo;

/**
 * Created by Jason on 2019/3/4.
 */
public class DeadLockDemoTest {
    public static void main(String[] args){
        String synchronizedOne = "one";
        String synchronizedTwo = "two";

        new Thread(){
            @Override
            public void run() {
                while(true){
                    synchronized (synchronizedOne){
                        System.out.println("开始1了，等待2");
                        synchronized (synchronizedTwo){
                            System.out.println("在一中执行二");
                        }
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    synchronized (synchronizedTwo){
                        System.out.println("开始2了，等待1");
                        synchronized (synchronizedOne){
                            System.out.println("在二中执行一");
                        }
                    }
                }
            }
        }.start();
    }
}
