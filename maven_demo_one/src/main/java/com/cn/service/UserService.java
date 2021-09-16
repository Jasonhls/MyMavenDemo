package com.cn.service;


import com.cn.pojo.User;

/**
 * Created by Jason on 2018/1/28.
 */
public interface UserService {
    User selectUserByUserName(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String username);

    String sayHello(String name,Integer age);
}
