package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-13 09:29
 **/
public class LocalVariableTest {

    public void localvarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        //new byte[10 * 1024 * 1024]不会被回收
        System.gc();
    }

    public void localvarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        //new byte[10 * 1024 * 1024]会被回收
        System.gc();
    }

    public void localvarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        //由于buffer占据过当前方法的局部变量表的槽位，所以new byte[10 * 1024 * 1024]被buffer引用着，导致该对象不能被回收
        System.gc();
    }

    public void localvarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        //这里已经不是buffer的作用域，因此下面的value会占据buffer之前在局部变量表中所占的槽位，因此上面的new byte[10 * 1024 * 1024]对象就没有被引用，会回收掉。
        int value = 10;
        System.gc();
    }

    public void localvarGC5() {
        localvarGC1();
        //new byte[10 * 1024 * 1024]会被回收
        System.gc();
    }

    public static void main(String[] args) {
        LocalVariableTest lvt = new LocalVariableTest();
        lvt.localvarGC5();
    }
}
