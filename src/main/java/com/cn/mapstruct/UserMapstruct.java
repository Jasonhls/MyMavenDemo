package com.cn.mapstruct;

import com.cn.pojo.User;
import com.cn.pojo.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapstruct {
    @Mapping(source = "username", target = "name")
    UserDTO userToUserDTO(User user);
}
