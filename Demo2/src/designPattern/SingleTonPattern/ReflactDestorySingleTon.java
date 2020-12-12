package designPattern.SingleTonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 *  破坏单例模式
 *      可以看到通过反射可以得到单例模式的private方法，从而进行新建对象，导致对象并非是单例的
 *
 *
 *      如何修改：
 *          我们将私有的构造方法内添加一句
 *          if(instance!=null){
 *              throw new RuntimeException("单例模式禁止反射调用")；
 *          }
 *          即可解决反射破坏饿汉式单例模式
 *
 */

public class ReflactDestorySingleTon {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        SingleTon_UnLazy s1 = SingleTon_UnLazy.getInstance();

        Constructor<SingleTon_UnLazy> constructor = SingleTon_UnLazy.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingleTon_UnLazy  s2 = constructor.newInstance();
        System.out.println(s1==s2); // false

    }

}
