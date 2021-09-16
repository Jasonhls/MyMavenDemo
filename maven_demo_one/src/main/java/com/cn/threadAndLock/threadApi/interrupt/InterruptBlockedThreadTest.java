package com.cn.threadAndLock.threadApi.interrupt;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 10:43
 **/
public class InterruptBlockedThreadTest {
    /**
     * 打断阻塞状态的线程
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            System.out.println("sleep...");
            try {
                /**
                 * 正常运行状态的线程被打断，线程的打断标识为true，
                 * 但是打断那些调用了Sleep，wait，join方法（这些方法会导致线程阻塞，线程变成阻塞状态）的线程，
                 * 会抛出异常，这时候，线程的打断标识会被清空，即变成false
                 */
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();

        //目的是主线程停会，然后让t1线程进入睡眠阻塞状态，这里有可能主线程睡了两秒中，
        // t1线程还没进入阻塞状态，这时候打断t1，打断标识为true
        Thread.sleep(2000);

        System.out.println("开始打断t1线程");
        //打断t1线程
        t1.interrupt();
        //线程对象的isInterrupted()方法用于获取线程的打断标识
        System.out.println("t1的打断标识为：" + t1.isInterrupted());
    }
}
