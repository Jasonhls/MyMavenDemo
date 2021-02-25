package com.cn.threadAndLock.relationInterface;

import java.util.concurrent.*;

/**
 * Created by Jason on 2019/3/4.
 */
public class CallableAndFutureTaskTest {
    public static void main(String[] args){
        //第一种方式 ExecutorService继承了executor
        //ThreadPoolExecutor是ExecutorService的子类
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task2 task2 = new Task2();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task2);
        executorService.submit(futureTask);
        executorService.shutdown();

        //第二种方式
        /*Task2 task22 = new Task2();
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(task22);
        Thread threadAndLock = new Thread(futureTask1);
        threadAndLock.start();*/

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task2运行结果为：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}

class Task2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0;i < 100;i++){
            sum += i;
        }
        return sum;
    }
}
