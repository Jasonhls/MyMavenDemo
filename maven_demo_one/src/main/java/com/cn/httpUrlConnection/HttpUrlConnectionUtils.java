package com.cn.httpUrlConnection;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @Author: helisen
 * @Date 2021/11/24 17:24
 * @Description:
 */
public class HttpUrlConnectionUtils {
    /**
     * 输入流转换成字符串
     * @param inputStream
     * @param charSet
     * @return
     */
    public static String isToString(InputStream inputStream, String charSet) {
        charSet = StringUtils.isNotBlank(charSet) ? charSet : "utf-8";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charSet);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String s;
            while((s = bufferedReader.readLine()) != null) {
                builder.append(s);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输入流转换成字符串
     * @param inputStream
     * @param charSet
     * @return
     */
    public static String isToString2(InputStream inputStream, String charSet) {
        charSet = StringUtils.isNotBlank(charSet) ? charSet : "utf-8";
        try {
            int len;
            byte[] arr = new byte[1024];
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            while((len = inputStream.read(arr)) != -1) {
                os.write(arr, 0, len);
            }
            return new String(os.toByteArray(), charSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
