package designPattern.SimpleFactory;

/**
 *
 * 工厂模式：
 *      创建型模式。他提供了一种创建对象的最佳方法。
 *      我们在创建对象时不会对客户端暴露创建逻辑，斌给时通过使用一个共同的接口在指向新创建的对象。
 *    意图：
 *      定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
 *    主要解决：
 *      主要解决接口选择问题
 *     使用场景：
 *      1.日志记录器：记录可能记录到本地硬盘、系统事件、远程服务器等，用户可以选择记录日志到什么地方。
 *      2.数据库访问，当用户不知道最后系统采用哪一类数据库，以及数据库可能有变化时。
 *      3.设计一个连接服务器框架，需要三个协议，"POP3","IMAP","HTTP"，可以将这三个作为产品类，共同实现一个接口
 *
 *
 */

public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // 获取circle对象，并调用他的draw方法
        Shape circle = shapeFactory.getShape("circle");
        // 调用draw方法
        circle.draw();

        // 获取Rectangle对象，并调用draaw方法
        Shape rectangle = shapeFactory.getShape("rectangle");
        rectangle.draw();

    }




}
