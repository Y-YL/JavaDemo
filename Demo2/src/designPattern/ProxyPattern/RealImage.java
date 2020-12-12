package designPattern.ProxyPattern;

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }
    // 从硬盘提前加载
    private void loadFromDisk(String fileName){
        System.out.println("Loading "+fileName);
    }

    @Override
    public void diaplay() {
        System.out.println("Displaying "+fileName);
    }
}
