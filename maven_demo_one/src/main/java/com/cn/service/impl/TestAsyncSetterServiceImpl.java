package com.cn.service.impl;

import com.cn.pojo.Result;
import com.cn.service.TestAsyncSetterService;
import org.springframework.stereotype.Service;

/**
 * Created by Jason on 2018/10/18.
 */
@Service
public class TestAsyncSetterServiceImpl implements TestAsyncSetterService {
    @Override
    public Result testTask(Result result, String name) {
        try {
            result.setState(true);
            result.setMessage("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setMessage("failed");
        }
        return result;
    }
}
