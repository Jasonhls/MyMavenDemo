package com.cn.jvm;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 16:46
 **/
public class TestStack {
    private int depth = 0;

    public void test() {
        this.depth++;
        test();
    }

    public static void main(String[] args) {
        TestStack testStack = new TestStack();
        try {
            testStack.test();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("调用栈的深度（次数）：" + testStack.depth);
        }
    }
}
