package com.cn.designMode.celue;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FanliService {
    HashMap<String,JisuanService> jisuanServiceHashMap = new HashMap<>();

    public FanliService(){

    }

    public FanliService(List<JisuanService> list){
        for (JisuanService jisuanService : list){
            jisuanServiceHashMap.put(jisuanService.tyep(),jisuanService);
        }
    }

    public double fanli(String type,double price){
        return jisuanServiceHashMap.get(type).jisuan(price);
    }
}
