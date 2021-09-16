package com.cn.maventwo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;

    private String username;

    private String password;

    /**
     * 是否启用,1表示启用，0表示不启用
     */
    private Integer enable;

    public User() {
    }

    public User(String username, String password, Integer enable) {
        this.username = username;
        this.password = password;
        this.enable = enable;
    }
}