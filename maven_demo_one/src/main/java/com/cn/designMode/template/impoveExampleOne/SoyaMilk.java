package com.cn.designMode.template.impoveExampleOne;

/**
 * @description:
 * @author: helisen
 * @create: 2021-02-26 11:19
 * 豆浆
 **/
public abstract class SoyaMilk {
    /**
     * 模板方法，make，模板方法可以做成final，不让子类去实现
     */
    public final void make() {
        select();
        if(customerWantCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }


    /**
     * 选材料
     */
    void select() {
        System.out.println("第一步：选择好的新鲜黄豆");
    }

    /**
     * 添加不同的配料，抽象方法，子类具体实现
     */
    abstract  void addCondiments();

    /**
     * 浸泡
     */
    void soak() {
        System.out.println("第三步：黄豆和配料开始浸泡，需要3个小时");
    }

    /**
     * 打碎
     */
    void beat() {
        System.out.println("第四步：将黄豆和配料放入豆浆机中打碎");
    }

    /**
     * 定义钩子方法，用于子类去重写
     * @return
     */
    boolean customerWantCondiments() {
        return true;
    }
}
