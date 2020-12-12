package designPattern.AdapterPattern;

public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        // 啥也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("playing mp4 file. Name:"+fileName);
    }
}
