package com.cn.threadAndLock.JUC.CAS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 18:19
 **/
public class AtomicArrayTest {
    public static void main(String[] args) {
        demo(
                () -> new int[10],
                (array) -> array.length,
                (array, index) -> array[index]++,
                array -> System.out.println(Arrays.toString(array))
        );
    }

    private static <T> void demo(
            Supplier<T> arraySupplier,
            Function<T, Integer> lengthFun,
            BiConsumer<T, Integer> putConsumer,
            Consumer<T> printConsumer) {
        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        Integer length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            ts.add(new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % length);
                }
            }));
        }

        //启动所有的线程
        ts.forEach(Thread::start);
        //等所有线程结束
        ts.forEach(t -> {
            while(true) {
                if(t.isInterrupted()) {
                    break;
                }
            }
        });

        printConsumer.accept(array);
    }
}
