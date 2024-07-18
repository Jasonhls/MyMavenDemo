package com.cn.controller;

import com.cn.pojo.User;
import com.cn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Jason on 2019/2/17.
 */
@RestController
@RequestMapping(value = "/test")
public class TestHahaController {
    @Autowired
    private TestService testService;
    @PostMapping(value = "/saveAndGet")
    public User saveAndGet(@RequestBody User user){
        return testService.saveAndGet(user);
    }

    @PostMapping(value = "/saveAndGetTwo")
    public User saveAndGetTwo(@RequestBody User user){
        return testService.saveAndGetTwo(user);
    }

    @GetMapping(value = "/testH")
    public String testH(){
        String phone = "12345678912";
        return phone.substring(0,phone.length() - 4) + "****";
    }

    @GetMapping(value = "/getSpringFactories")
    public Map getSpringFactories(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> urls = classLoader.getResources("META-INF/spring.factories");
            LinkedMultiValueMap result = new LinkedMultiValueMap();
            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                UrlResource urlResource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(urlResource);
                Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<Object, Object> next = iterator.next();
                    List<String> strings = Arrays.asList(StringUtils.commaDelimitedListToStringArray((String) next.getValue()));
                    result.addAll((String)next.getKey(),strings);
                }
            }
            return result;
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", e);
        }
    }
}
