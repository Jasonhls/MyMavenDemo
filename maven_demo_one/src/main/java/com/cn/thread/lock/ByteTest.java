package com.cn.thread.lock;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-28 14:38
 **/
public class ByteTest {
    public static void main(String[] args) {
        int s = 0b00000000000000100000000000000011;
        int ss= 0b00000000000000110000000000000010;
        //将高16位抹去，取低16位
        int write = s & 0x0000FFFF;
        //0b00000000000000110000000000000000;
        int writeTwo = s<<16;
        System.out.println("ss：" + ss);
        System.out.println("writeTwo：" + writeTwo);
        //无符号补0右移16位
        int read = s>>>16;
        System.out.println(s);
        System.out.println(write);
        System.out.println(read);

        int i = 0b00000000000000100000000000000011;
        int ii = 0b00000000000000100000000000000000;
        System.out.println(i);
        System.out.println(ii);
        int m = i & 0x0000FFFF;
        int mm = i & 0xFFFF0000;
        System.out.println(m);
        System.out.println(mm);

        int SHARED_SHIFT = 16;
         int t = 0b00000000000000010000000000000000;
        System.out.println("t：" + t);
        //   1 << SHARED_SHIFT 其值就是65536，即与t相等
        int c =  (1 << SHARED_SHIFT) - 1;
        int cc = 0x0000FFFF;
        System.out.println("cc：" + cc);
        System.out.println("c：" + c);

        int b = 0b00000000000000010000000000000000;
        System.out.println("哈哈1：" + b);
        System.out.println("之前的状态：" + (b>>>16));
        //下面操作是高16位加一，高16位减一的方法也是 b - (1<<16)
        int bb = b + (1<<16);
        System.out.println("哈哈2：" + bb);
        //截取高16位
        System.out.println("之后的状态：" + (bb>>>16));

        System.out.println("--" + (1 << SHARED_SHIFT));

        /**
         * int b = 0x00000000000000100000000000000001
         * 总结：截取高16位，b>>>16，截取低16位，b&0x0000FFFF
         * 高16位加减一，b + (1<<16)，b - (1<<16)
         * 低16位加减一，b + 1， b - 1
         */


    }

    @Test
    public void testM2() {
        int n = 0b00001111;
        System.out.println(n);
        n |= n >>> 1;
        System.out.println(n >>> 1);
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n >>> 2);
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n >>> 4);
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n >>> 8);
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n >>> 16);
        System.out.println(n);

        int m = 0b1001;
        System.out.println(m);
        int j = 0b0111;
        System.out.println(j);
        m |= j;
        System.out.println(m);
    }

    @Test
    public void testM3() {
        System.out.println(tableSizeFor(511));
//        System.out.println(tableSizeFor(10));
//        System.out.println(tableSizeFor(16));
//        System.out.println(tableSizeFor(20));

    }

    //获取比C大，且是2的幂等数
    private static final int tableSizeFor(int c) {
        int MAXIMUM_CAPACITY = 1 << 30;

//        0b00001001 00001101 00001000 00000011
//
//        0b00001001 00001101 00001000 00000010
//        0b00000100 10000110 10000100 00000001
//    ---0b00001101 10001111 10001100 00000011
//
//        0b00000010 01000011 01000010 00000000
//    ---0b00001101 10001111 10001100 00000011
//    ---0b00001111 11001111 11001110 00000011
//
//        0b00000000 10010000 11010000 10000000
//    ---0b00001111 11001111 11001110 00000011
//    ---0b00001111 11011111 11011110 10000011
//
//        0b00000000 00001001 00001101 00001000
//    ---0b00001111 11011111 11011110 10000011
//    ---0b00001111 11011111 11011111 10001011
//
//
//        0b00000000 00000000 00001001 00001101
//    ---0b00001111 11011111 11011111 10001011
//    ---0b00001111 11011111 11011111 10001111

        int n = c - 1;
        System.out.println(n);
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void testM4() {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
    }
}
