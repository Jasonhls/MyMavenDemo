package com.cn.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description: 想要原子地更新字段类需要两步，第一步，因为原子更新字段类都是抽象类，每次使用的时候必须使用
 * 静态方法newUpdater()创建一个更新器，并且需要设置想要更新的类和属性，第二步，更新类的字段(属性)必须使用public volatile修饰符
 * @author: helisen
 * @create: 2020-06-02 09:57
 **/
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public static void main(String[] args) {
        User conan = new User("conan", 10);
        System.out.println(a.getAndIncrement(conan));
        System.out.println(a.get(conan));
    }

    public static class User {
        private String name;
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
