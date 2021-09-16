package com.cn.threadAndLock.threadDesignMode.guardedSuspension;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GuardObject {
    //结果
    private Object response;

    //获取结果
    public Object get() {
        synchronized (this) {
            //如果没有结果
            while(response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        GuardObject guardObject = new GuardObject();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "等待结果");
            List<String> list = (List<String>)guardObject.get();
            System.out.println(Thread.currentThread().getName() + "结果大小：" + list.size());
        }, "t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行下载");
            try {
                List<String> list = downLoad();
                guardObject.complete(list);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "t2").start();
    }

    public static List<String> downLoad() throws Exception{
        HttpURLConnection conn = (HttpURLConnection)new URL("https://www.baidu.com/").openConnection();
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
