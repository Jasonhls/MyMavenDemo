package com.cn.dataStructure;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args){
        TreeSet ts = new TreeSet();
        ts.add("1");
        ts.add("2");
        ts.add("3");

        for (Object str : ts){
            String s = (String)str;
            System.out.println("遍历方式一："+s);
        }

        Object[] objects = ts.toArray();
        for (Object st : objects){
            String str = (String) st;
            System.out.println("遍历方式二："+str);
        }

        Iterator iterator = ts.iterator();
        while(iterator.hasNext()){
            System.out.println("遍历方式三："+iterator.next());
        }

    }
}
