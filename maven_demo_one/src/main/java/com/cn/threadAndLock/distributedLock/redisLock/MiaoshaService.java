package com.cn.threadAndLock.distributedLock.redisLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Created by Jason on 2019/3/16.
 */
@Service
public class MiaoshaService {
    @Autowired
    DatabaseService databaseService;
    @Autowired
    RedisTemplate redisTemplate;

//    Lock lock = new ReentrantLock();
    Lock lock = null;

    @PostConstruct
    public void init(){
        lock = new RedisLockImpl(redisTemplate,"goods_code_bike",1);
    }

    /**
     * 秒杀具体实现
     * @param goodsCode 商品编码
     * @param uerId 用户id
     * @return
     */
    public boolean miaosha(String goodsCode,final String uerId){
        lock.lock();
        boolean result = false;
        try {
            result = databaseService.buy(goodsCode, uerId);
            System.out.println("秒杀结果：" + result);
            if(result){
                //更新仓库
                //示例：如果秒杀成功，更新一次库存(库存一般是在缓存里面，供页面快速查询)
                String count = databaseService.getCount(goodsCode);//注意如果是分库，需要把写库中的数据同步到读库，这样查询出来的数据才是最新的
                //模拟线程运行延时，随机睡眠
                if(Integer.valueOf(count) % 2 == 1)Thread.sleep(new Random().nextInt(500));
                redisTemplate.opsForValue().set(goodsCode,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return result;
    }
}
