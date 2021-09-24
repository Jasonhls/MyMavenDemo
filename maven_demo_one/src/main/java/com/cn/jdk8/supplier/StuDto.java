package com.cn.jdk8.supplier;

import java.util.function.Supplier;

/**
 * @Author: helisen
 * @Date 2021/9/24 9:25
 * @Description:
 */
public class StuDto {
    private Supplier<String> supplier;
    private String name;
    private int age;

    /*public StuDto() {
    }

    public StuDto(String name, int age) {
        this.name = name;
        this.age = age;
    }*/

    public StuDto(Supplier<String> supplier, int age) {
        this.supplier = supplier;
        this.age = age;
    }

    public String getName() {
        return supplier.get();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
