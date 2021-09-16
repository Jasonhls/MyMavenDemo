package com.cn.service.impl;

import com.cn.pojo.User;
import com.cn.service.TestService;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2019/2/17.
 * 4： PROPAGATION_REQUIRES_NEW
 这个就比较绕口了。 比如我们设计ServiceA.methodA的事务级别为PROPAGATION_REQUIRED，ServiceB.methodB的事务级别为PROPAGATION_REQUIRES_NEW，
 那么当执行到ServiceB.methodB的时候，ServiceA.methodA所在的事务就会挂起，ServiceB.methodB会起一个新的事务，等待ServiceB.methodB的事务完成以后，
 他才继续执行。他与PROPAGATION_REQUIRED 的事务区别在于事务的回滚程度了。因为ServiceB.methodB是新起一个事务，那么就是存在
 两个不同的事务。如果ServiceB.methodB已经提交，那么ServiceA.methodA失败回滚，ServiceB.methodB是不会回滚的。如果ServiceB.methodB失败回滚，
 如果他抛出的异常被ServiceA.methodA捕获，ServiceA.methodA事务仍然可能提交。
 * 模拟事务的隔离，* 方法A（外层），方法B（内存）
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserService userService;


    /**
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User saveAndGet(User user) {
        userService.saveUser(user);
        userService.deleteUser("tony");
        int i = 1 / 0;
        return userService.selectUserByUserName("haha1");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User saveAndGetTwo(User user) {
        try {
            userService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        userService.deleteUser("tony");
        return userService.selectUserByUserName("haha1");

    }

    @Override
    public List<Map<String, Object>> getCodeBatch(List<String> list) {
        List<Map<String,Object>> li = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (int i = 0;i < list.size();i++){
            map.put("code",list.get(i));
            map.put(list.get(i),i);
        }
        li.add(map);
        return li;
    }

    @Override
    public Map<String,Object> getCode(String code) {
        Map map = new HashMap();
        map.put("hls","lalala");
        return map;
    }
}
