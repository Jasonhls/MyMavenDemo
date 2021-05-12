package com.cn.jvm;

/**
 * @description: 如何快速判断是否发生了逃逸，就看new的对象是否有可能在方法外部被调用
 * @author: helisen
 * @create: 2021-05-10 12:28
 **/
public class EscapeAnalysis {
    public EscapeAnalysis obj;

    /**
     * 方法返回EscapeAnalysis对象，发生逃逸
     * @return
     */
    public EscapeAnalysis getInstance() {
        return obj == null ? new EscapeAnalysis() : obj;
    }

    /**
     * 为成员变量赋值，发生逃逸
     * 如果当前的obj引用声明为static的？仍然会发生逃逸。
     */
    public void setObj() {
        this.obj  = new EscapeAnalysis();
    }

    /**
     * 对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useEscapeAnalysis() {
        EscapeAnalysis e = new EscapeAnalysis();
    }

    /**
     * 引用成员变量的值，发生逃逸
     */
    public void useEscapeAnalysis1() {
        EscapeAnalysis e = getInstance();
    }
}
