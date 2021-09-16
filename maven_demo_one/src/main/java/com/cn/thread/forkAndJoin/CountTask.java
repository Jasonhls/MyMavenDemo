package com.cn.thread.forkAndJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-02 09:18
 **/
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 2;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        long sum = 1;
        //如果任务足够小就直接计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for (long i = start; i <= end; i++){
                sum *= i;
            }
        }else {
            //如果任务大于阀值，就分裂成两个子任务计算
            long middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行结果，并得到其结果
            long leftResult = leftTask.join();
            long rightResult = rightTask.join();
            //合并子任务
            sum = leftResult * rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1,30);
        Future<Long> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
