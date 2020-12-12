package designPattern.ProxyPattern;

public class ProxyPatternDemo {

    public static void main(String[] args) {

        Image image = new ProxyImage("test_10.jpg");
        // 图像从磁盘加载
        image.diaplay();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.diaplay();
    }

}
