package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 14:41
 **/
public class ClassLoaderTest {
    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);

        //获取其上层：扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader@6fadae5d
        System.out.println(extClassLoader);

        //获取其上层：获取不到引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        //没法获取，结果为null
        System.out.println(bootstrapClassLoader);


        //对于用户自定义类来说：默认使用系统类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);

        //String类使用引导类加载器进行加载的。---->Java的核心类库都是使用引导类加载器进行加载的
        ClassLoader stringClassLoader = String.class.getClassLoader();
        //null
        System.out.println(stringClassLoader);
    }
}
