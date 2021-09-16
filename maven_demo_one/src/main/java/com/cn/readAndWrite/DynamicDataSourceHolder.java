package com.cn.readAndWrite;

public class DynamicDataSourceHolder {
    //使用threadLocal记录当前线程的数据源key
    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<>();

    public DynamicDataSourceHolder() {
    }

    /**
     * 设置数据源
     * @param dataSourceGlobal
     */
    public static void putDataSource(DynamicDataSourceGlobal dataSourceGlobal){
        holder.set(dataSourceGlobal);
    }

    /**
     * 获取当前线程中的数据源
     * @return
     */
    public static DynamicDataSourceGlobal getDataSource(){
        return holder.get();
    }

    /**
     * 清除当前线程的数据源
     */
    public void removeDataSource(){
        holder.remove();
    }
}
