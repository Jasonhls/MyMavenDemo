package com.cn.jdk8.biFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @Author: helisen
 * @Date 2021/9/26 17:27
 * @Description:
 */
public class BiFunctionDemo {

    public static void main(String[] args) {
        //定义一个函数，BiFunction<T, U, R>，其中T和U都是入参，R为出参
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x * y;
        System.out.println(biFunction.apply(3, 7));


        Person p1 = new Person(20, "zhangsan");
        Person p2 = new Person(30, "lisi");
        Person p3 = new Person(40, "wangwu");
        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

        List<Person> list1 = getPersonByName("zhangsan", personList);
        List<Person> list2 = getPersonByAge(20, personList);
        System.out.println(list1);
        System.out.println(list2);

        List<Person> list3 = getPersonByAge2(20, personList, (age, list) -> list.stream().filter(p -> p.getAge() > age).collect(Collectors.toList()));
        System.out.println(list3);


    }

    public static List<Person> getPersonByName(String name, List<Person> personList) {
        return personList.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
    }

    public static List<Person> getPersonByAge(int age, List<Person> personList) {
        return personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList());
    }

    //更加灵活的方式，让调用者实现过滤条件，是大于还是小于
    public static List<Person> getPersonByAge2(int age, List<Person> personList,
                                               BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, personList);
    }
}
