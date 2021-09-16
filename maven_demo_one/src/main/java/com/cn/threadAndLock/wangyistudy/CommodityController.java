package com.cn.threadAndLock.wangyistudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping(value = "/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    private static final int THREAD_NUM = 1000;
    private CountDownLatch latch = new CountDownLatch(THREAD_NUM);

    @GetMapping(value = "/test")
    public String testCommodity() throws IOException {
        for (int i = 0;i < THREAD_NUM;i++){
            final String code = "code-" + (i + 1);
            Thread thread = new Thread(()->{
                try {
                    latch.await();
                    Map<String, Object> result = commodityService.queryCode(code);
//                    Map<String, Object> result = commodityService.queryOneCode(code);
                    if(result != null){
                        System.out.println("返回的结果：" + result.toString());
                    }else {
                        System.out.println("查询结果为空。。");
                    }
                }catch (Exception e){
                    System.out.println(Thread.currentThread().getName() + " 线程执行出现异常");
                }
            });
            thread.setName("price-threadAndLock-" + code);
            thread.start();
            latch.countDown();
        }
        return "ok";
        //输入任意键退出
//        System.in.read();
    }

    @GetMapping(value = "/thread")
    public void testThread(){
        System.out.println("开始");
        for (int i = 0;i < THREAD_NUM;i++){
            Thread thread = new Thread(()->{
                try {
                    latch.await();
                    System.out.println("大家好");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("price-threadAndLock-" + i);
            thread.start();
            latch.countDown();
        }
        System.out.println("结束");
    }
}
