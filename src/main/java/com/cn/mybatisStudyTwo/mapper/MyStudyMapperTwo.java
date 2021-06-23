package com.cn.mybatisStudyTwo.mapper;

import com.cn.mybatisStudy.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface MyStudyMapperTwo {
    User getUserById(@Param("id") Long id);
}
