package designPattern.SingleTonPattern;

/*
    懒汉式，不支持多线程
 */
public class SingleTon_Lazy {

    private static SingleTon_Lazy instance;

    private SingleTon_Lazy(){

    }

    public static SingleTon_Lazy getInstance(){
        if (instance == null){
            instance = new SingleTon_Lazy();
        }
        return instance;
    }

}
