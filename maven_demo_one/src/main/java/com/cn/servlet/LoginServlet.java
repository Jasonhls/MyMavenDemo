package com.cn.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jason on 2018/11/12.
 * //在springboot框架中，注入servlet类的方法有两种：
 * 第一：使用注解@WebServlet，同理@WebFilter,@WebListener也是一样的，另外需要在启动类上加上@ServletComponentScan才行
 * 第二：单独定义一个类，然后在配置文件中使用@Bean注册该类
 */
@WebServlet(name = "/loginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code1 = req.getParameter("checkcode");
        String code2 = (String) req.getSession().getAttribute("checkcode");
        if(code1 != null && code2 != null && !code1.equals(code2)){
            req.setAttribute("state","验证码错误");
        }else{
            req.setAttribute("state","继续登录");
        }
        resp.sendRedirect("/session/login");//转发就不在同一次请求里，信息会丢失
//        super.doGet(req, resp);//这个必须去掉
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login servlet doPost");
        super.doPost(req, resp);
    }
}
