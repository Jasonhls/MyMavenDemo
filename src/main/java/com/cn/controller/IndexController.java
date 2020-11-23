package com.cn.controller;

import com.cn.websocket.MyWebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @description:
 * @author: helisen
 * @create: 2020-11-23 09:39
 **/
@Controller
public class IndexController {


    //网站首页
    @RequestMapping("/index")
    public String echart(ModelMap modelMap) {
        modelMap.addAttribute("test","niubi");
        return "/index";
    }

    @RequestMapping("/indexTwo")
    public String indexTwo(ModelMap modelMap) {
        modelMap.addAttribute("test","niubi");
        return "/indexTwo";
    }



    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{sname}")
    public String pushToWeb(@PathVariable String sname, String message) {
        try {
            MyWebSocket.sendInfo(sname,message);
        } catch (IOException e) {
            e.printStackTrace();
            return "推送失败";
        }
        return "推送成功"+sname;
    }




}