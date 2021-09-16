package com.cn.rabbitMQ.study2;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jason on 2019/3/28.
 */
@Data
public class RabbitmqOrder implements Serializable{
    private static final long serialVersionUID = -2221214252163879885L;

    private String orderId; // 订单id

    private Integer orderStatus; // 订单状态 0：未支付，1：已支付，2：订单已取消

    private String orderName; // 订单名字
}
