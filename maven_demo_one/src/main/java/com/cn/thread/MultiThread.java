package com.cn.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-18 11:25
 **/
public class MultiThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (int i = 0; i < threadInfos.length; i ++){
            System.out.println("threadId: " + threadInfos[i].getThreadId() + "，threadName: " + threadInfos[i].getThreadName());
        }
    }
}
