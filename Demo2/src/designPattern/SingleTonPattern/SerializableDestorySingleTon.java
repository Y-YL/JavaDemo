package designPattern.SingleTonPattern;

import java.io.*;

public class SerializableDestorySingleTon {

    public static void main(String[] args) throws Exception{

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // true

        // 1. 将对象序列化到硬盘
        FileOutputStream fos = new FileOutputStream("d:/output.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);

        // 关闭流
        oos.close();
        fos.close();

        // 2.反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/output.txt"));
        Singleton s3 = (Singleton) ois.readObject();
        System.out.println(s1 == s3);

    }

}



class Singleton implements Serializable{
    // 1. 私有化构造函数
    private Singleton(){

    }
    // 2.在类的内部创建实例
    private static Singleton instance = new Singleton();
    //3 .提供获取唯一实例的方法
    public static Singleton getInstance(){
        return instance;
    }

    // 解决反序列化破解单例的问题，当有readResolve方法时，在反序列化时，直接将这个对象返回
    // 如果没有这段代码，在反序列化的时候，就会生成一个新的对象从而破坏单例模式
    private Object readResolve() throws ObjectStreamException{
        return instance;
    }

}
