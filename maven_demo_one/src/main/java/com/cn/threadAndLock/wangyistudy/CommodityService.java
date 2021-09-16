package com.cn.threadAndLock.wangyistudy;

import com.cn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class CommodityService {
    class Request{
        String commodityCode;
        CompletableFuture<Map<String,Object>> future;
    }
    @Autowired
    private TestService testService;

    BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void init(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //下面的方法每10微秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(()->{
            //实现逻辑
            int size = queue.size();
            if(size == 0)return;
            ArrayList<Request> requests = new ArrayList<>();
            for (int i = 0;i < size;i++){
                Request request = queue.poll();//从queue中取出request，然后放到requests中
                requests.add(request);
            }
            System.out.println("批量处理数据：" + size);
            ArrayList<String> commodityCodes = new ArrayList<>();
            for (Request request : requests){
                commodityCodes.add(request.commodityCode);
            }
            //需要调用远程接口的批量查询方法
            List<Map<String, Object>> reponses = testService.getCodeBatch(commodityCodes);
            //[
            //{code=code-273, phone=huawei, price=4000, isOk=true, commodityId=658617225},
            //{code=code-273, phone=huawei, price=4000, isOk=true, commodityId=658617225}
            //]
            //-->map -->code:result --->map.get(request.code)
            HashMap<String,Map<String,Object>> responseMap = new HashMap<>();
            for (Map<String,Object> response : reponses){
                String code = response.get("code").toString();
                response.remove("code");
                if(code != null){
                    System.out.println("code对应的值 " + response.toString());
                }else {
                    System.out.println("response为空");
                }
                responseMap.put(code,response);
            }
            //值怎么返回给调用线程？？
            //callable-->JDK API -->future<T> --->T就是我们的结果-->get() 阻塞直到有返回值为止。
            for (Request request : requests){
                Map<String,Object> result = responseMap.get(request.commodityCode);
                request.future.complete(result);
            }
        },0,10, TimeUnit.MILLISECONDS);
    }


    public Map<String,Object> queryCode(String code) throws Exception {
        Request request = new Request();
        request.commodityCode = code;
        CompletableFuture<Map<String,Object>> future = new CompletableFuture<>();
        request.future = future;
        queue.add(request);//把request放到queue中
        System.out.println("结果："+ request.future.get());
        return request.future.get();
    }

    public Map<String,Object> queryOneCode(String code){
        return testService.getCode(code);
    }
}
