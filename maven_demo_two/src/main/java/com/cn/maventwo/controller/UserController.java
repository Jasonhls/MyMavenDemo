package com.cn.maventwo.controller;

import com.cn.maventwo.pojo.User;
import com.cn.maventwo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jason on 2018/1/28.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String haveFun() {
        return "hello";
    }

    @GetMapping(value = "/{username}")
    public User findUserByUserName(@PathVariable String username) {
        User user = userService.selectUserByUserName(username);
        return user;
    }

    @PostMapping(value = "/add")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping(value = "/update")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping(value = "/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    @GetMapping(value = "/encrypt")
    public String crypt(@RequestBody User user) {
        return "hello, " + user.getUsername() + "-encrypt";
    }
}
