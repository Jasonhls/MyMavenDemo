package com.cn.maventwo.mapper;

import com.cn.maventwo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Jason on 2018/1/28.
 */
@Mapper
public interface UserMapper {
    User selectUserByUserName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String username);
}
