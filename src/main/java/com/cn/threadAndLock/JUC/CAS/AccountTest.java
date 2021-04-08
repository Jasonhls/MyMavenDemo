package com.cn.threadAndLock.JUC.CAS;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: helisen
 * @create: 2021-04-08 16:48
 **/
public class AccountTest {

    public static void main(String[] args) {
        Account account1 = new AccountUnsafe(10000);
        Account.demo(account1);

        Account account2 = new AccountCas(10000);
        Account.demo(account2);

        DecimalAccount account3 = new DecimalAccountCas(new BigDecimal("10000"));
        DecimalAccount.demo(account3);
    }

}

class DecimalAccountCas implements DecimalAccount {
    private AtomicReference<BigDecimal> balance;

    public DecimalAccountCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while(true) {
            BigDecimal prev = balance.get();
            BigDecimal next = prev.subtract(amount);
            if(balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

class AccountCas implements Account {

    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        /*while(true) {
            //获取余额最新值
            int prev = balance.get();
            //要修改的余额
            int next = prev - amount;
            //真正修改
            if(balance.compareAndSet(prev, next)) {
                break;
            }
        }*/
        balance.getAndAdd( -1 * amount);
    }
}

/**
 * 通过方法上添加synchronized，或使用ReentrantLock都可以解决问题
 */
class AccountUnsafe implements Account {
    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        synchronized (this) {
            return this.balance;
        }
    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }
}

interface Account {
    //获取余额
    Integer getBalance();
    //取款
    void withdraw(Integer amount);

    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);

        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + "，耗时：" + (end - start)/1000_000 + " ms");
    }
}

interface DecimalAccount {
    //获取余额
    BigDecimal getBalance();
    //取款
    void withdraw(BigDecimal amount);

    static void demo(DecimalAccount account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(new BigDecimal(10));
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);

        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + "，耗时：" + (end - start)/1000_000 + " ms");
    }
}
