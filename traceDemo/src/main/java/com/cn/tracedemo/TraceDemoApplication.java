package com.cn.tracedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/12/8 15:05
 */
@SpringBootApplication
@EnableAsync
public class TraceDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TraceDemoApplication.class, args);
    }
}
