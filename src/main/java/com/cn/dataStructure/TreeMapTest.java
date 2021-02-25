package com.cn.dataStructure;

import java.util.Map;
import java.util.TreeMap;

/**
 * TreeMap的用法可以直接百度
 */
public class TreeMapTest {
    public static void main(String[] args){
        TreeMap tm = new TreeMap();
        tm.put("1","demo1");
        tm.put("2","demo2");
        tm.put("3","demo3");
        /**
         * public Map.Entry<K,V> ceilingEntry(K key)
         * 返回指定的Key大于或等于的最小值的元素，如果没有，则返回null
         */
        Map.Entry entry = tm.ceilingEntry("2.5");
        System.out.println(entry.getKey() + "================" + entry.getValue());
        Map.Entry entry1 = tm.ceilingEntry("4");
        System.out.println(entry1);

        /**
         * public K ceilingKey(K key)
         *  返回指定的Key大于或等于的最小值的Key，如果没有，则返回null
         */
        Object obj = tm.ceilingKey("2.5");
        System.out.println(obj);
        obj = tm.ceilingKey("5");
        System.out.println(obj);

        /**
         * public Object clone()
         * 返回集合的副本
         */
        Object objj = tm.clone();
        System.out.println("treeMap集合中所有的元素：" + tm);
        System.out.println("treeMap的副本：" + objj);
        TreeMap ttm = (TreeMap)objj;
        System.out.println(ttm.get("2"));

        /**
         * public Comparator<? super K> comparator()
         * 如果使用默认的比较器，就返回null，如果使用其他的比较器，则返回比较器的哈希码值
         */

    }
}
