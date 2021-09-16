package com.cn.threadAndLock.threadCorrespond;

/**
 * Created by Jason on 2019/3/4.
 */
public class ThreeThreadCorrespondTest {
    public static void main(String[] args){
        Printer2 p = new Printer2();
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        p.printer1();
                    } catch (InterruptedException e) {
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

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        p.printer3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
