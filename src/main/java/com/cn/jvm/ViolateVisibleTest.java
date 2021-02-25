package com.cn.jvm;

public class ViolateVisibleTest {

    private static boolean initFlag = false;

    public static void main(String[] args) throws Exception{
        new Thread(()->{
            System.out.println("waiting data...");
            while(!initFlag){

            }
            System.out.println("...... sucess");
        }).start();

        Thread.sleep(2000);

        new Thread(()->{
            prepareData();
        }).start();
    }

    public static void prepareData(){
        System.out.println("start prepare data");
        initFlag = true;
        System.out.println("end prepare data");
    }
}
