package com.cn.rabbitMQ.study2;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jason on 2019/3/25.
 */
@Data
public class RabbitmqUser implements Serializable{
    private static final long serialVersionUID = -1262627851741431084L;
    private String userId;
    private String name;
}
