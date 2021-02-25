package com.cn.threadAndLock.threadCorrespond;

/**
 * Created by Jason on 2019/3/4.
 */
public class TwoThreadCorrespondTest {
    public static void main(String[] args){
        Printer p = new Printer();
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        p.printer1();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        p.printer2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
