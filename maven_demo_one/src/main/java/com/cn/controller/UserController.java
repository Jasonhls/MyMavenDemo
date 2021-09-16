package com.cn.controller;

import com.cn.mapper.UserMapper;
import com.cn.pojo.User;
import com.cn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jason on 2018/1/28.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String haveFun() {
        return "hello";
    }

    @GetMapping(value = "/{username}")
    public User findUserByUserName(@PathVariable String username) {
        User user = userService.selectUserByUserName(username);
        return user;
    }

    @PostMapping(value = "")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping(value = "")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping(value = "/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }


    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/thread")
    public void testThread(@RequestBody User user){
        new Thread(){
            @Override
            public void run() {
                userService.updateUser(user);
            }
        }.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(){
            @Override
            public void run() {
                System.out.println("线程二进来了");
                synchronized (this){
                    System.out.println("执行线程二的逻辑了");
                    user.setPassword("123");
                    userMapper.updateUser(user);
                    System.out.println("线程二走完。。。");
                }
            }
        }.start();
    }

    @GetMapping(value = "/testHaha")
    public String testHahs(int i) throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        if(i == 0){
            c.countDown();
        }
        c.await(10, TimeUnit.SECONDS);//如果c中count没有到0，会执行这行代码，否则不会执行
        log.info("-------countDownLatch-----------");
        return "haha";
    }

    @GetMapping(value = "/testMyCache")
    public String sayHello(String name,Integer age){
        return userService.sayHello(name,age);
    }
}
