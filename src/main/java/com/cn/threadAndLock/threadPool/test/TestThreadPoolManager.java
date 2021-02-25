package com.cn.threadAndLock.threadPool.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by Jason on 2018/10/17.
 * 所有的订单必须先加入到cacheMap中，如果满了，就添加到缓冲队列中
 */
@Component
public class TestThreadPoolManager implements BeanFactoryAware {
    /*用于从IOC里面取出对象*/
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    //线程池维护线程的最少数量
    private static int CORE_POOL_SIZE = 2;
    //线程池维护线程的最大数量
    private static int MAX_POOL_SIZE = 10;
    //线程池维护线程所允许的空闲时间
    private static int KEEP_ALIVE_TIME = 0;
    //线程池所使用的缓冲队列大小
    private static int WORK_QUEUE_SIZE = 50;

    /**
     * 用于存储在队列中的订单，防止重复提交，在真实场景中，可用redis代替，验证重复
     */
    Map<String,Object> cacheMap = new ConcurrentHashMap<String,Object>();

    /**
     * 订单的缓冲队列，当线程池满了，则将订单存入到此缓冲队列中
     */
    Queue<Object> msgQueue = new LinkedBlockingQueue<Object>();

    /*
    * 如果线程池满了，会调用下面的方法，将多余的任务添加到缓冲队列中
    */
    final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //订单加到缓冲队列
            msgQueue.offer(((BusinessThread)r).getAcceptStr());
            System.out.println("系统任务态忙了，把此订单交给(调度线程池)逐一处理，订单号：" + ((BusinessThread)r).getAcceptStr());
        }
    };

    /*创建线程池*/
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE
                ,MAX_POOL_SIZE
                ,KEEP_ALIVE_TIME
                , TimeUnit.SECONDS
                ,new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE)
                ,this.handler);

    /*将任务添加到订单线程池中*/
    public void addOrders(String orderId){
        if(cacheMap.get(orderId) == null){
            cacheMap.put(orderId,new Object());
            BusinessThread businessThread = new BusinessThread(orderId);
            executor.execute(businessThread);
        }
    }

    /**
     * 线程池的定时任务----> 称为(调度线程池)。此线程池支持定时以及周期性执行任务的需求
     */
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    /**
     * 检查(调度线程池)，每秒执行一次，查看订单的缓冲队列是否有订单记录，如果有则取出来，重新添加到订单线程池中去，
     * 注意它本身不执行下单，下单还是订单线程池去执行
     */
    final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            //判断缓冲队列中是否存在记录
            if(!msgQueue.isEmpty()){
                //线程池的队列容量少于WORK_QUEUE_SIZE,则开始把缓冲队列中的订单加入到线程池
                if(executor.getQueue().size() < WORK_QUEUE_SIZE){
                    String orderId = (String) msgQueue.poll();//
                    BusinessThread businessThread = new BusinessThread(orderId);
                    executor.execute(businessThread);
                    System.out.println("(调度线程池)缓冲队列出现订单业务，重新添加到线程池，订单号：" + orderId);
                }
            }
        }
    },0,1,TimeUnit.SECONDS);

    /*终止订单线程池以及调度线程池*/
    public void shutDown(){
        System.out.println("终止订单线程池和调度线程池：" + scheduledFuture.cancel(false));
        scheduler.shutdown();//终止调度线程池
        executor.shutdown();//终止订单线程池
    }

    /*获取缓冲线程池*/
    public Queue getMsgQueue(){
        return this.msgQueue;
    }
}
