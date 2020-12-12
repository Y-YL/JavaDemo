package designPattern.SingleTonPattern;

/**
 * 双锁机制，安全且在多线程情况下能保持高性能。
 *
 * 单例模式使用场景：
 *      1.需要生成唯一序列的环境
 *      2.需要频繁实例化然后销毁的对象
 *      3.创建对象时耗时过多或者耗资源过多，但又经常用到的对象。
 *      4.方便资源相互通信的环境
 *
 */

public class SingleTon_DCL {
    // 注意不加volatile会出现问题
    private volatile static SingleTon_DCL singleton;
    private SingleTon_DCL(){};

    public static SingleTon_DCL getSingleton(){
        if (singleton == null){
            synchronized (SingleTon_DCL.class){
                if (singleton == null){
                    singleton = new SingleTon_DCL();
                }
            }
        }
        return singleton;
    }

}
