package designPattern.AdapterPattern;

public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("playing vlc file. Name:"+fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // 啥也不做
    }
}
