package com.cn.service.impl;

import com.cn.cache.annotationCache.MyCache;
import com.cn.cache.annotationCache.NeteaseEduCache;
import com.cn.mapper.UserMapper;
import com.cn.pojo.User;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jason on 2018/1/28.
 */
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @NeteaseEduCache(key = "'netease_' + #username")
    public User selectUserByUserName(String username) {
        System.out.println("从数据库中查询了用户信息");
        return userMapper.selectUserByUserName(username);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveUser(User user) {
        System.out.println("开始执行线程一");
        userMapper.saveUser(user);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束执行线程一");
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        System.out.println("哈哈，线程一中");
        userMapper.updateUser(user);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程一走完。。。");
    }

    @Transactional()
    @Override
    public void deleteUser(String username) {
        userMapper.deleteUser(username);
    }

    @Override
    @MyCache(value = "'myCache_' + #name + '_' + #age")
    public String sayHello(String name, Integer age) {
        return "hello " + name + " you age is " + age;
    }
}
