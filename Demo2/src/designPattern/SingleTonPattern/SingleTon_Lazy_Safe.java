package designPattern.SingleTonPattern;

public class SingleTon_Lazy_Safe {

    private static SingleTon_Lazy_Safe instance;

    private SingleTon_Lazy_Safe(){

    }

    public static synchronized SingleTon_Lazy_Safe getInstance(){
        if (instance == null){
            instance = new SingleTon_Lazy_Safe();
        }
        return instance;
    }
}
