package com.cn.threadAndLock.distributedLock.redisLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Jason on 2019/3/16.
 */
@RestController
@RequestMapping(value = "/miaosha")
public class MiaoshaController {

    @Autowired
    MiaoshaService miaoshaService;

    /**
     * 跳转首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/")
    public String index(ModelMap model, HttpServletRequest request){
        String message = "当前服务器窗口：" + request.getLocalAddr() + ":" + request.getLocalPort();
        model.put("message",message);
        return "home";
    }

    /**
     * 秒杀接口
     * @param goodsCode
     * @param userId
     * @return
     */
    @RequestMapping(value = "/miaosha")
    @ResponseBody
    public Object getUserInfo(String goodsCode,String userId){
        return miaoshaService.miaosha(goodsCode,userId);
    }

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/test")
    public void teste(String value){
        redisTemplate.opsForValue().set("bike",value);
    }

    @Autowired
    MiaoshaMapper miaoshaMapper;

    @GetMapping(value = "/update")
    public int update(String goodsCode){
        return miaoshaMapper.update(goodsCode);
    }

    @GetMapping(value = "/getGoodsNumByGoodsCode")
    public String getGoodsNumByGoodCode(String goodsCode){
        return miaoshaMapper.getGoodsNumByGoodsCode(goodsCode) + "";
    }

    @GetMapping(value = "/miaoshaTwo")
    public void miaosha() {
        //模拟的请求数量
        final int threadNum = 200;
        //倒计时器，用于模拟高并发（信号枪机制）
        CountDownLatch cdl = new CountDownLatch(threadNum);
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            String userId = "Tony";
            Thread thread = new Thread(() -> {
                try {
                    cdl.await();
                    //http 请求实际上就是多线程调用这个方法
                    miaoshaService.miaosha("bike", userId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i] = thread;
            thread.start();
            //倒计时器 减一
            cdl.countDown();
        }
    }
}
