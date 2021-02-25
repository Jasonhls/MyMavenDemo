package com.cn.kuaidi;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kuaidi")
public class KuaidiController {
    @PostMapping(value = "/query100")
    public String queryKuaidi100(@RequestBody QueryKuaidiDTO queryKuaidiDTO){
//        String key = queryKuaidiDTO.getKey();				//贵司的授权key
//        String customer = queryKuaidiDTO.getCustomer();			//贵司的查询公司编号
        String key = "gnOvjzVM6545";
        String customer = "5DE1DAF599799A48B85BC4D14ECA36C9";
        String com = queryKuaidiDTO.getCom();			//快递公司编码
        String num = queryKuaidiDTO.getNum();	//快递单号
        String phone = "";				//手机号码后四位
        String from = "";				//出发地
        String to = "";					//目的地
        int resultv2 = 0;				//开启行政规划解析

        Kd100TrackQuery demo = new Kd100TrackQuery(key, customer);
        String result = demo.synQueryData(com, num, phone, from, to, resultv2);
        System.out.println(result);
        return result;
    }

    /**
     * 快递鸟
     * @param expCode
     * @param expNo
     * @return
     */
    @GetMapping(value = "/queryKuaidiniao")
    @ResponseBody
    public String queryKuaidiniao(String expCode,String expNo){
        KdniaoTrackQuery api = new KdniaoTrackQuery();
        String result = null;
        try {
            result = api.getOrderTracesByJson(expCode, expNo);
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
