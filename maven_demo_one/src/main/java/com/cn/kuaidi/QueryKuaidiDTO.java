package com.cn.kuaidi;

import lombok.Data;

@Data
public class QueryKuaidiDTO {
    String key;				    //贵司的授权key
    String customer;			//贵司的查询公司编号
    String com;			        //快递公司编码
    String num;	                //快递单号
    String phone;				//手机号码后四位
    String from;				//出发地
    String to;					//目的地
    int resultv2 = 0;				//开启行政规划解析
}
