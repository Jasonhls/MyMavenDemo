package com.cn.httpUrlConnection;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: helisen
 * @Date 2021/11/24 16:39
 * @Description:
 */
@SpringBootTest
public class HttpUrlConnectionTest {
    //get请求
    @Test
    public void getRequestDemo() {
        InputStream inputStream = null;
        try {
            //必须添加http，不然url格式有问题
            String urlPath = "http://127.0.0.1:8893/test/hello/sayHello";
            URL url = new URL(urlPath);
            //得到connection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流
                inputStream = connection.getInputStream();
                //将响应流转换成字符串
                String result = HttpUrlConnectionUtils.isToString2(inputStream, "utf-8");
                System.out.println("get请求返回值：" + result);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //post请求
    @Test
    public void postRequestDemo() {
        InputStream inputStream = null;
        try {
            String urlPath = "http://localhost:8893/test/hello/getStudent";
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");//设置请求方式为POST
            connection.setDoOutput(true);//允许写出
            connection.setDoInput(true);//允许读入
            connection.setUseCaches(false);//不适用缓存
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                String result = HttpUrlConnectionUtils.isToString2(inputStream, "utf-8");
                System.out.println("post请求返回值：" + result);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //post请求带有参数
    @Test
    public void postRequestWithParamsDemo() {
        InputStream inputStream = null;
        try {
            String urlPath = "http://localhost:8893/test/hello/getStudent";
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");//设置请求方式为POST
            connection.setDoOutput(true);//允许写出
            connection.setDoInput(true);//允许读入
            connection.setUseCaches(false);//不适用缓存
            connection.connect();

            String body = "name=zhangsan&age=15";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "utf-8"));
            writer.write(body);
            writer.close();

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                String result = HttpUrlConnectionUtils.isToString2(inputStream, "utf-8");
                System.out.println("post请求返回值：" + result);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //post请求带有json串格式的参数
    @Test
    public void postRequestWithJsonParamsDemo() {
        InputStream inputStream = null;
        try {
            String urlPath = "http://localhost:8893/test/hello/updateStudent";
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.connect();

            String json = "{\"name\":\"123\",\"age\":18}";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.write(json);
            writer.close();

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                String result = HttpUrlConnectionUtils.isToString2(inputStream, "utf-8");
                System.out.println(result);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //上传文件
    @Test
    public void postRequestUploadFileDemo() {
        InputStream inputStream = null;
        try {
            String urlPath = "http://gateway.test.bberp.xyb2b.com.cn/helper/oss/uploadFile";
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");//设置请求方式为POST
            connection.setDoOutput(true);//允许写出
            connection.setDoInput(true);//允许读入
            connection.setUseCaches(false);//不适用缓存
            connection.setRequestProperty("Content-Type", "file/*");//设置数据类型
            connection.connect();

            OutputStream outputStream = connection.getOutputStream();
            //文件输入流
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\EDZ\\Desktop\\pdf\\test_1.doc");//把文件封装成一个流
            int length;
            byte[] arr = new byte[1024];
            while((length = fileInputStream.read(arr)) != -1) {
                outputStream.write(arr, 0, length);
            }
            fileInputStream.close();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                String result = HttpUrlConnectionUtils.isToString2(inputStream, "utf-8");
                System.out.println("post请求返回值：" + result);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //下载文件
    @Test
    public void getRequestDownloadFileDemo() {
        InputStream inputStream = null;
        try {
            String urlPath = "https://www.baidu.com/";
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                File dir = new File("C:\\Users\\EDZ\\Desktop\\pdf");
                if(!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, "fileName.html");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] arr = new byte[1024 * 8];
                int len;
                while((len = (inputStream.read(arr))) != -1) {
                    fos.write(arr, 0, len);
                }
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
