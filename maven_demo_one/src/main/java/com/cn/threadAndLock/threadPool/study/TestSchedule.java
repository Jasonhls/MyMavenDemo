package com.cn.threadAndLock.threadPool.study;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 12:26
 **/
public class TestSchedule {
    /**
     * 如何让每周四18:00:00定时执行任务如何让每周四18:00:00定时执行任务
     * @param args
     */
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        //now这一周的周五12点38分0秒0纳秒
        LocalDateTime time = now.withHour(12).withMinute(38).withSecond(0).withNano(0).with(DayOfWeek.FRIDAY);

        //如果当前时间 > 本周四，必须找到下一周
        if(now.compareTo(time) > 0) {
            time = time.plusWeeks(1);
        }

        long initailDelay = Duration.between(now, time).toMillis();

        //任务执行间隔时间
//        long period = 1000 * 60 * 60 *24 * 7;
        long period = 1000;

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(() -> {
            System.out.println("hahaha");
        }, initailDelay, period, TimeUnit.MILLISECONDS);
    }
}
