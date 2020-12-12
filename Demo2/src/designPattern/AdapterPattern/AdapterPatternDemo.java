package designPattern.AdapterPattern;

/**
 *  适配器模式：  - 结构型模式
 *      适配器模式作为两个不兼容的接口之间的桥梁。它结合了两个独立接口的功能。
 *      这种模式设计到一个单一的类，该类负责加入独立的不兼容的接口功能。
 *   意图：
 *      将一个类的接口转换成客户希望的另外一个接口。
 *      适配器模式使得原来由于接口不兼容而不能一起工作的那些类可以一起工作。
 *   应用实例：
 *      1.美国电器110v，中国220V，需要适配器
 *      2.Linux中运行WINDOWS程序
 *      3.JAVA中的jdbc
 */
public class AdapterPatternDemo {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3","beyond the horizon.mp3");
        audioPlayer.play("mp4","alone.mp4");
        audioPlayer.play("vlc","far far away.vlc");
        audioPlayer.play("avi","mind me.avi");
    }

}
