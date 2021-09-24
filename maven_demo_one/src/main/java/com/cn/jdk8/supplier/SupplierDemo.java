package com.cn.jdk8.supplier;

import java.util.function.Supplier;

/**
 * @Author: helisen
 * @Date 2021/9/24 9:23
 * @Description:
 */
public class SupplierDemo {
    public static void main(String[] args) {
        /*Supplier<StuDto> supplier = StuDto::new;
        //调用两次得到的两个对象不相同
        StuDto stuDto = supplier.get();
        StuDto stuDto1 = supplier.get();
        System.out.println(stuDto);
        System.out.println(stuDto1);*/


        //例子2
        A a = new A();
        StuDto stuDto = a.createStuDto();
        System.out.println(stuDto.getName());

    }

    static class A {
        public StuDto createStuDto() {
            //this::getResult得到Supplier<String>作为入参，在后面调用到supplier.get()方法的时候，才会真正调用到this.getResult()方法
            StuDto stuDto = new StuDto(this::getResult, 30);
            return stuDto;
        }

        protected String getResult() {
            StringBuilder stringBuilder = new StringBuilder("name");
            return stringBuilder.append("_").append("haha").toString();
        }
    }



}
