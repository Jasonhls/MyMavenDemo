package com.cn.threadAndLock.threadPool.study;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-09 13:48
 **/
public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(pool.invoke(new MyTask(5)));

        System.out.println(pool.invoke(new AddTask3(1, 10)));
    }
}

//1~n之间整数求和
class MyTask extends RecursiveTask<Integer> {
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    /**
     * 这个方法会被递归调用，直至满足终止条件
     * @return
     */
    @Override
    protected Integer compute() {
        //终止的条件
        if(n == 1) {
            System.out.println(Thread.currentThread().getName() + n);
            return 1;
        }
        MyTask t1 = new MyTask(n - 1);
        t1.fork();//让一个线程去执行此任务
        System.out.println(Thread.currentThread().getName() + n + ", t1 " + t1);

        //获取任务结果
        int result = n + t1.join();
        System.out.println(Thread.currentThread().getName() + n + ", t1 " + t1 + ", result " + result) ;
        return result;
    }
}

//任务拆分优化
class AddTask3 extends RecursiveTask<Integer> {
    int begin;
    int end;

    public AddTask3(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "AddTask3{" +
                "begin=" + begin +
                ", end=" + end +
                "} " + super.toString();
    }

    @Override
    protected Integer compute() {
        if(begin == end) {
            System.out.println(Thread.currentThread().getName() + "，join：begin " + begin);
            return begin;
        }
        if(end - begin == 1) {
            System.out.println(Thread.currentThread().getName() + "，join：begin " + begin + ", end " + end);
            return end + begin;
        }
        int mid = (end + begin) / 2;
        AddTask3 t1 = new AddTask3(begin, mid);
        t1.fork();
        AddTask3 t2 = new AddTask3(mid + 1, end);
        t2.fork();
        System.out.println(Thread.currentThread().getName() + "，fork：t1 " + t1 + ", t2" + t2);

        int result = t1.join() + t2.join();
        System.out.println(Thread.currentThread().getName() + "，t1" + t1 + ", t2 " + t2 + ", result " + result);
        return result;
    }
}
