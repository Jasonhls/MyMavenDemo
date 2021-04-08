package com.cn.threadAndLock.JUC.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAccessor {
    static Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    static Unsafe getUnsafe() {
        return unsafe;
    }
}
