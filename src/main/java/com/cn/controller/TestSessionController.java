package com.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Jason on 2018/11/12.
 */
@RestController
@RequestMapping(value = "/session")
public class TestSessionController {
    @GetMapping(value = "/test")
    public void testSession(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map map = new HashMap();
        HttpSession session = request.getSession();
        session.setAttribute("name","hls");
        response.sendRedirect("/session/login");
    }

    @GetMapping(value = "/login")
    public Map login(HttpServletRequest request){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        map.put("sessionId",session.getId());
        map.put("name",session.getAttribute("name"));
        map.put("createTime",getSessionCreateTime(session));
        return map;
    }

    @GetMapping(value = "/genereateCode")
    public void genereateCode(HttpServletRequest request){
        Random random = new Random();
        String code  = random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10);
        System.out.println(code);
        request.getSession().setAttribute("checkcode",code);
    }

    public String getSessionCreateTime(HttpSession session){
        long creationTime = session.getCreationTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date(creationTime));
        return format;
    }


}
