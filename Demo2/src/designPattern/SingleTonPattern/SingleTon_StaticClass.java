package designPattern.SingleTonPattern;

/**
 * 静态内部类方式：
 *          既能享受类加载确保线程安全带来的便利，又能延迟加载的方式，就是静态内部类。
 *  Java静态内部类的特性是，加载的时候不会加载内部静态类，使用的时候才会加载。而使用到的时候类加载
 *  又是线程安全的。
 *  注释： 类加载时机：JAVA虚拟机在有且仅有以下五种场景下会对类进行初始化
 *      1. 遇到new、getstatic、setstatic、或者invokestatic这四个字节码指令时，
 *          对应的Java代码场景为：new一个关键字或者一个实例化对象时、读取或设置一个静态字段时（
 *          final修饰、已在编译期把结果放入常量池的除外）、调用一个类的静态方法
 *      2. 使用java。lang。reflact包的方法对类进行反射调用的时候，如果类没进行初始化，需要先调用其初始化方法进行初始化。
 *      3. 当初始化一个类时，如果其父类还没初始化，会先触发其父类的初始化
 *      4. 当虚拟机启动时，用户需要指定一个要执行的朱磊（包含main（）方法的类），虚拟机会先初始化这个类
 *      5. 当使用JDK1.7等动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getStatic、
 *         REF_putStatic、REF_invokeStatic的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化
 *         除此之外的所有引用类都不会对类进行初始化，称为被动引用。静态内部类就属于被动引用的行列。
 *
 *      所以在此单例模式中，只有getInstance（）方法被调用的时候， SingleTon_StaticClass才在  SingleTonHolder 的运行时常量里面
 *      把符号引用替换为直接引用，这时静态对象instance才被创建，相当于饿汉式单例
 *
 *      ？？？： 虚拟机如何保证其的安全性
 *          虚拟机会保证一个类的<client>()方法在多线程环境中被正确的加锁、同步，如果多个线程同时去初始化一个类，那么只会有
 *          一个线程去执行这个类的<client>()方法，其他线程都需要阻塞等待，直到活动线程执行<client>()方法完毕。如果一个类
 *          的<client>()方法中有耗时很长的操作，就可能造成多个进程阻塞(需要注意的是，其他线程虽然会被阻塞，但如果一个类的
 *          <client>()方法执行过后，其他线程唤醒后不会再次进入该方法。同一个加载器，一个类型只会初始化一次)
 *
 *          故而，可以看出该单例在创建过程中是线程安全的，所以说静态内部类形式可保证线程安全，也能保证单例的唯一性，
 *          同时也延迟了单例的实例化
 *
 */
public class SingleTon_StaticClass {

    private static class SingletonHolder{
        private static SingleTon_StaticClass instance = new SingleTon_StaticClass();
    }
    private SingleTon_StaticClass(){

    }
    public static SingleTon_StaticClass getInstance(){
        return SingletonHolder.instance;
    }

}
