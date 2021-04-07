package com.cn.threadAndLock.threadDesignMode.producerAndConsumer;

import java.util.LinkedList;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 10:01
 **/
public class ProducerAndConsumerTest {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "值" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.take();
            }
        }, "消费者线程aaa").start();
    }
}

/**
 * 消息队列，java线程之间的通信，跟rabbitmq这种在进程间通信的消息队列是不一样的
 */
class MessageQueue {
    //消息的队列集合，用双向队列
    private LinkedList<Message> list = new LinkedList<>();
    //队列容量
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    //获取消息
    public Message take() {
        //注意这里的加锁对象与里面的wait和notifyAll的调用者必须是同一个对象，这里用list
        synchronized (list) {
            //检查队列是否
            while(list.isEmpty()) {
                try {
                    System.out.println(Thread.currentThread().getName() + "，队列为空，消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列头部获取消息，并返回
            Message message = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + "，已消费消息");
            list.notifyAll();
            return message;
        }
    }

    //存入消息
    public void put(Message message) {
        //注意这里的加锁对象与里面的wait和notifyAll的调用者必须是同一个对象，这里用list
        synchronized (list) {
            //检查队列是否已经满了
            while(list.size() == capacity) {
                try {
                    System.out.println(Thread.currentThread().getName() + "，队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列尾部添加消息
            list.addLast(message);
            System.out.println(Thread.currentThread().getName() + "，已生产消息");
            list.notifyAll();
        }
    }
}

final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
