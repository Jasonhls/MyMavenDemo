package com.cn.maventwo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.cn.maventwo.mapper"})
public class MyMavenTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyMavenTwoApplication.class, args);
    }
}
