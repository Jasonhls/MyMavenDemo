package com.cn.designMode.celue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/jisuan")
public class JisuanController {

    /*属性注入的方式1*/
    /*private VIPService vipService;
    public JisuanController(VIPService vipService){
        this.vipService = vipService;
    }*/

    /*属性注入方式2*/
    @Autowired
    private VIPService vipService;

    @Autowired
    SVIPService svipService;

    @GetMapping(value = "/jisuan")
    public double jisuan(String type,Double price){
        if("VIP".equals(type)){
            return vipService.jisuan(price);
        }else if("SVIP".equals(type)){
            return svipService.jisuan(price);
        }
        return 0d;
    }

    @Autowired
    private FanliService fanliService;

    @PostConstruct
    public void initFanliService(){
        List<JisuanService> list = new ArrayList<>();
        list.add(vipService);
        list.add(svipService);
        this.fanliService = new FanliService(list);
    }

    @GetMapping(value = "/fanli")
    public double fanli(String type,double price){
        return fanliService.fanli(type,price);
    }

}
