package com.cn.service;


import com.cn.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2019/2/17.
 */
public interface TestService {
    User saveAndGet(User user);

    User saveAndGetTwo(User user);

    List<Map<String,Object>> getCodeBatch(List<String> list);

    Map<String,Object> getCode(String code);
}
