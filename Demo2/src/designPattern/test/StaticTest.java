package designPattern.test;

/**
 * 加载顺序：
 *  1. 记载到静态遍历 test new了一个实例
 *      加载非静态方法
 *      初始化非静态变量
 *      执行非静态代码块
 *      执行构造函数
 *   2. 加载到静态代码块 - 执行
 *   3. 到了main函数里 new了一个新实例
 *      加载非静态方法
 *      初始化非静态变量
 *      执行非静态代码块
 *      执行构造方法
 */

public class StaticTest {

    static StaticTest test = new StaticTest();

    static {
        System.out.println("block A");
    }
    {
        System.out.println("block B");
    }

    public static void main(String[] args) {
        StaticTest t = new StaticTest();
    }

}
