package com.cn.threadAndLock.AtomicAndLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CasLock {
    private volatile int value;
    static Unsafe unsafe;
    static long valueOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            valueOffset = unsafe.objectFieldOffset(CasLock.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(){
        int current;
        do {
            //根据属性内存地址相对于类内存地址的偏移量获取属性value的值
            current = unsafe.getIntVolatile(this,valueOffset);
        }while(
            //加一操作，如果操作的时候，内存中value当前值等于上一步获取的current，那么就会操作成功，返回true
            //如果操作不成功，说明内存中value当前值不等于上一步获取的current,返回false，接着循环重新获取
            //内存中value的值。
            !unsafe.compareAndSwapInt(this,valueOffset,current,current+1)
        );
    }

    public static void main(String[] args) throws InterruptedException {
        CasLock d = new CasLock();
        for (int i = 0;i < 2;i++){
            new Thread(()->{
                    for (int j = 0;j < 1000;j++){
                        d.add();
                    }
                }
            ).start();
        }
        Thread.sleep(2000L);
        System.out.println(d.value);
    }
}
