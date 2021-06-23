package com.cn.mybatisStudyTwo.controller;

import com.cn.mybatisStudy.pojo.User;
import com.cn.mybatisStudyTwo.mapper.MyStudyMapperTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private MyStudyMapperTwo myStudyMapperTwo;

    @GetMapping(value = "/test")
    public User getUserById(Long id) {
        return myStudyMapperTwo.getUserById(id);
    }
}
