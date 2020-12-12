package designPattern.SingleTonPattern;

/**
 * 饿汉式
 * 无需加锁，执行效率较高
 * 类加载时就初始化，浪费内存
 */

public class SingleTon_UnLazy {

    private static SingleTon_UnLazy instance = new SingleTon_UnLazy();
    private SingleTon_UnLazy(){};
    public static SingleTon_UnLazy getInstance(){
        return instance;
    }
}
