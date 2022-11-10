package com.cn.request;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/11/10 17:41
 */
@Slf4j
@RestController(value = "/attributeTransfer")
public class RequestAttributeTransferTest {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10,1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));


    @GetMapping(value = "/test")
    public void test() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Map<String, Object> values = ThreadLocalHelper.getValues();
        log.info("当前线程名1：{}， values的值：{}", Thread.currentThread().getName(), JSONUtil.toJsonStr(values));
        log.info("当前线程名1：{}， attributes的值：{}", Thread.currentThread().getName(), JSONUtil.toJsonStr(attributes));
        executor.execute(() -> {
            ThreadLocalHelper.setValues(values);
            RequestContextHolder.setRequestAttributes(attributes);
            String s = testA();
            System.out.println(s);
        });
    }

    private String testA() {
        Map<String, Object> values = ThreadLocalHelper.getValues();
        log.info("当前线程名2：{}， values的值：{}", Thread.currentThread().getName(), JSONUtil.toJsonStr(values));
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("当前线程名2：{}， token的值：{}", Thread.currentThread().getName(), JSONUtil.toJsonStr(attributes));
        HttpServletRequest request = attributes.getRequest();
        if(request != null) {
            String header = request.getHeader("x-token");
            System.out.println("测试结果：" + header);
        }
        return "success haha";
    }
}
