package com.cn.classAndInterface;

public class STest {
    public static void main(String[] args) {
        //未使用到构造，只是使用到SSClass类和其成员变量，所以只是初始化SSClass类就可以了
//        System.out.println(SSClass.a);
        //用到了SubClass类，以及其父类SSClass的成员变量a，还是只是初始化SSClass类就可以了
//        System.out.println(SubClass.a);
        //用到了SuperClass类，以及其父类SSClass的成员变量，还是只是初始化SSClass类就可以了
//        System.out.println(SuperClass.a);
        //用到了SuperClass类的成员变量，所有需要初始化SuperClass类，然后还初始化了SSClass类
        System.out.println(SubClass.b);
    }
}
