package com.cn.String;

import java.util.HashMap;
import java.util.Set;

public class HashAndEqualTest {
    public static void main(String[] args) {
        StrStudent s = new StrStudent("name1",1);
        StrStudent s2 = new StrStudent("name2",2);
        HashMap h = new HashMap();
        String ss= "123";
        h.put(s,ss);
        h.put(s2,ss);
        Set<StrStudent> set1 = h.keySet();
        for (StrStudent g : set1){
            System.out.println(g.getName() + " " + g.getAge());
            System.out.println(h.get(g));
        }
        System.out.println(h.toString());
    }
}
