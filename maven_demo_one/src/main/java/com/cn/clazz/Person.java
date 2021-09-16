package com.cn.clazz;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-02 16:44
 **/
public class Person {
    private String name;

    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String sayHello() {
        return "hello " + name;
    }

    public String eat(String food) {
        return name + " eat " + food;
    }
}
