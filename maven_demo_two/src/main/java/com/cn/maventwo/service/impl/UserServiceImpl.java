package com.cn.maventwo.service.impl;

import com.cn.maventwo.mapper.UserMapper;
import com.cn.maventwo.pojo.User;
import com.cn.maventwo.service.UserService;
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
    public User selectUserByUserName(String username) {
        System.out.println("从数据库中查询了用户信息");
        return userMapper.selectUserByUserName(username);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
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
    public String sayHello(String name, Integer age) {
        return "hello " + name + " you age is " + age;
    }
}
