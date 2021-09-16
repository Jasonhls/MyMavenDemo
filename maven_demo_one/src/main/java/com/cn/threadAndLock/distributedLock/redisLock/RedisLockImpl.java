package com.cn.threadAndLock.distributedLock.redisLock;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.lang.Nullable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 2019/3/20.
 */
public class RedisLockImpl implements Lock{
    RedisTemplate redisTemplate;
    String resourceName; //你要锁的资源 例如：table_goodsinfo_code_bike
    int timeout;

    public RedisLockImpl(RedisTemplate redisTemplate, String resourceName,int timeout) {
        this.redisTemplate = redisTemplate;
        this.resourceName = resourceName;
        this.timeout = timeout;
    }

    @Override
    public boolean tryLock() {
        //尝试获取锁
        //set命令，往redis存放锁的标志
        Boolean lockResult = (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                String value = "~";
                Boolean result = redisConnection.set(resourceName.getBytes(), value.getBytes(),
                        Expiration.seconds(timeout), RedisStringCommands.SetOption.SET_IF_ABSENT);
                return result;
            }
        });
        return lockResult;
    }

    Lock lock = new ReentrantLock();

    @Override
    public void lock() {//一直要抢到锁为止
        lock.lock();
        try {
            //抢redis锁的代码，同时只会有一个线程
            while(!tryLock()){//反复去重置key，如果可以，那么表示抢锁成功。下面用了订阅别的jvm释放锁的消息，如果得到通知，才去抢，不然等待timeout后，再去抢，这样做提高了效率
                //反复尝试，过一段时间之后再去尝试

                //订阅指定的redis主题，接受释放锁的信号
                redisTemplate.execute(new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        try {
                            CountDownLatch waiter = new CountDownLatch(1);
                            // subscribe 立马返回，是否订阅完毕，subscribe异步触发，相当于开启了一个线程
                            //订阅消息
                            redisConnection.subscribe(new MessageListener() {
                                /*只有收到了消息，才会执行下面的onMessage方法*/
                                @Override
                                public void onMessage(Message message, @Nullable byte[] bytes) {
                                    //收到消息，不管结果，立刻再次抢锁
                                    waiter.countDown();//计数器减一
                                }
                            },("release_lock_" + resourceName).getBytes());
                            /**
                             * 上面这部分代码，如果获取到了信息，那么立马会执行waiter.countDown()，
                             * 下面的waiter.await方法就不会执行，不然没有获取到消息，下面会等timeout
                             */
                            waiter.await(timeout,TimeUnit.SECONDS);//如果计数器为0或者等了timeout后，才会继续执行下面的代码
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 0L;
                    }
                });
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void unlock() {//释放锁，就发布一个消息，通知其他的client
        //删掉key，资源锁标记
        redisTemplate.delete(resourceName);
        //通过redis发布订阅机制发送一个通知给其他等待的请求
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //发布消息
                Long received = redisConnection.publish(("release_lock_" + resourceName).getBytes(), "~".getBytes());
                return received;
            }
        });
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
