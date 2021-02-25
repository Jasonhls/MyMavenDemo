package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2018/2/11.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @RequestMapping("/demo")
    public String start(){
        return "/fileDemo";
    }
    @RequestMapping("/upload")
    public String uploadFile(HttpServletRequest req, ModelMap model, MultipartHttpServletRequest multiReq){
        Map map = new HashMap();
        //获取文件上传路径
        String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
        MultipartFile file1 = multiReq.getFile("file1");
        //截取上传文件的文件名
        String uploadName = "";
        if (!StringUtils.isEmpty(uploadFilePath) && uploadFilePath.contains("\\")){
            uploadName = uploadFilePath.substring(uploadFilePath.indexOf("\\") + 1, uploadFilePath.lastIndexOf("."));
        }else {
            uploadName = uploadFilePath.substring(0,uploadFilePath.lastIndexOf("."));
        }
        //截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.lastIndexOf(".") + 1, uploadFilePath.length());
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try{
            fis = (FileInputStream) multiReq.getFile("file1").getInputStream();
            //fos = new FileOutputStream(new File("C:\\Users\\Jason\\Desktop\\文件上传\\123\\" + uploadName + "." + uploadFileSuffix));
            //相对路径是E:\SpringBoot视频教程\springboot练习路径\WebDemoOne，然后后面加上uploadName，后缀，uploadFileSuffix
            fos = new FileOutputStream(new File("./" + uploadName + "." + uploadFileSuffix));
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while(i != -1){
                fos.write(temp,0,temp.length);
                fos.flush();
                i = fis.read(temp);
            }
            map.put("msg","上传成功");
            map.put("state",true);
            model.addAttribute("map",map);
            return "test";
        }catch (IOException e){
            e.printStackTrace();
            map.put("msg","上传失败");
            map.put("state",false);
            model.addAttribute("map",map);
            return "test";
        }finally {
            if(fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
