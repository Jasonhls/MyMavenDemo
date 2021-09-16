package com.cn.threadAndLock.threadDesignMode.guardedSuspension;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-07 09:11
 **/
public class GuardedObjectWithBatch {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new People("people" + i).start();
        }

        Thread.sleep(1000);

        for (Integer id : Mailboxes.getIds()) {
            new Postman(id, "大家好" + id, "postman" + id).start();
        }
    }

}
class People extends Thread {
    /**
     * name表示线程名
     * @param name
     */
    public People(String name) {
        super(name);
    }

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        System.out.println(Thread.currentThread().getName() + "开始收信：" + guardedObject.getId());
        Object mail = guardedObject.get(5000);
        System.out.println(Thread.currentThread().getName() + "收到信 id " + guardedObject.getId() + ", 内容为：" + mail);

    }
}

class Postman extends Thread {
    private int id;
    private String mail;

    /**
     * name表示线程名
     * @param id
     * @param mail
     * @param name
     */
    public Postman(int id, String mail, String name) {
        super(name);
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        System.out.println(Thread.currentThread().getName() + "送信 id：" + guardedObject.getId() + ", 内容：" + mail);
        guardedObject.complete(mail);
    }
}

/**
 * Mailboxes这个类用于解耦结果产生的线程与接收的线程
 */
class Mailboxes {
    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();
    private static int id = 1;
    private static synchronized int generateId() {
        return id++;
    }

    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static GuardedObject getGuardedObject(int id) {
        return boxes.remove(id);
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

class GuardedObject {
    private int id;

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //结果
    private Object response;

    //获取结果，在一定时间内，超过这个时间，直接返回null
    public Object get(long timeout) {
        synchronized (this) {
            //记录开始时间
            long begin = System.currentTimeMillis();
            //记录经历的时间
            long passedTime = 0;
            //如果没有结果
            while(response == null) {
                //经历的时间超过最大等待时间，退出循环
                if(passedTime >= timeout) {
                    break;
                }
                try {
                    this.wait(timeout - passedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //求得经历时间
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    //产生结果
    public void complete(Object response) {
        synchronized (this) {
            //给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }
}
