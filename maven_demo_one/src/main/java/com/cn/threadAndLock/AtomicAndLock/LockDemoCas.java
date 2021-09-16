package com.cn.threadAndLock.AtomicAndLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Jason on 2019/3/2.
 */
public class LockDemoCas {
    //volatile表示可见性，如果该变量被一个线程修改，会被立即将修改后的结果同步到内存中，
    // 这样其他线程可以看到修改后的值
    volatile int value;

//    static Unsafe unsafe = Unsafe.getUnsafe();java中不允许这样获取，那只有通过反射

    static Unsafe unsafe;
    static long valueOffset;

    static {
        try {
            //反射获取该类中的theUnsafe字段
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe)declaredField.get(null);

            //属性的偏移量，用它定位在内存中 一个对象的具体属性的内存位置
            //valueOffset表示LockDemoCas类中，value属性内存地址相对与类LockDemoCas内存地址的偏移量
            valueOffset = unsafe.objectFieldOffset(LockDemoCas.class.getDeclaredField("value"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(){
        int current;
        do {
            //直接根据当前对象和value属性在内存中，相当于该对象的偏移量来获取value值，不能使用this.value
            current = unsafe.getIntVolatile(this, valueOffset);//获取当前value的值
            //这里进行compareAndSwapInt方法(是一个native方法)的时候，有可能在上一行代码执行后，value的值又发生了改变，
            // 这个方法可能就会失败，失败返回false，所以需要重复执行，compareAndSwapInt执行成功返回true，
            // 执行失败返回false
            //参数说明，第一个参数：对象，第二个参数：该对象需要改变的属性在内存中的地址相对于该对象在内存中地址的偏移量，
            //第三个参数：该属性的当前值，第四个参数：需要更新之后的值
        }while (!unsafe.compareAndSwapInt(this,valueOffset,current,current+1));
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemoCas d = new LockDemoCas();
        for (int i = 0;i < 2;i++){
            new Thread(
                    () -> {
                      for (int j = 0;j < 10000;j++){
                          d.add();
                      }
                    }
            ).start();
        }
        Thread.sleep(2000L);//这里让主线程睡2秒，不急着打印d.value，因为两个副线程可能还没执行完，等他们两都执行完，再打印d.value
        System.out.println(d.value);
    }
}
