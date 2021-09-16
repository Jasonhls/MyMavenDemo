package com.cn.ioc;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-01 11:21
 **/
public class Person {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Person() {
        System.out.println("调用person的构造函数");
    }
}
