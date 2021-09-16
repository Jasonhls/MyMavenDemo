package com.cn.jvm;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-11 14:40
 **/
public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 1024);
        System.out.println("直接内存分配完毕，请求指示！");
        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("直接内存开始释放！");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }
}
