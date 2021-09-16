package com.cn.String;

public class StrStudent {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StrStudent() {
    }

    public StrStudent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        StrStudent s = (StrStudent) obj;
        if(name.equals(s.name) && age == s.age){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
