package com.cn.jvm;

import com.sun.net.ssl.internal.ssl.Provider;
import sun.misc.Launcher;
//import sun.security.ec.CurveDB;

import java.net.URL;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 15:00
 **/
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        /**
         * file:/D:/jdk/java/jdk1.8.0_192/jre/lib/resources.jar
         * file:/D:/jdk/java/jdk1.8.0_192/jre/lib/rt.jar
         * file:/D:/jdk/java/jdk1.8.0_192/jre/lib/sunrsasign.jar
         * file:/D:/jdk/java/jdk1.8.0_192/jre/lib/jsse.jar
         * file:/D:/jdk/java/jdk1.8.0_192/jre/lib/jce.jar
         * file:/D:/jdk/java/jdk1.8.0_192/jre/lib/charsets.jar
         * file:/D:/jdk/java/jdk1.8.0_192/jre/lib/jfr.jar
         * file:/D:/jdk/java/jdk1.8.0_192/jre/classes
         */
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }

        //com.sun.net.ssl.internal.ssl.Provider是jsse.jar包里面的一个类，该类的类加载器为引导类加载器，即BootstrapClassLoader
        ClassLoader classLoader = Provider.class.getClassLoader();
        //打印为null，即表示为引导类加载器
        System.out.println(classLoader);

        System.out.println("******扩展类加载器******");
        String extDirs = System.getProperty("java.ext.dirs");
        /**
         * D:\jdk\java\jdk1.8.0_192\jre\lib\ext
         * C:\WINDOWS\Sun\Java\lib\ext
         */
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }

//        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        //sun.misc.Launcher$ExtClassLoader@42d80b78
//        System.out.println(classLoader1);
    }
}
