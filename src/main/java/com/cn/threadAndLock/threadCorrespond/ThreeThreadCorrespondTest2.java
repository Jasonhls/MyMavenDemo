package com.cn.threadAndLock.threadCorrespond;

/**
 * Created by Jason on 2019/3/5.
 */
public class ThreeThreadCorrespondTest2 {
    public static void main(String[] args){
        Printer3 p = new Printer3();
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
