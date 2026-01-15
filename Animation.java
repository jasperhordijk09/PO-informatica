import greenfoot.*;

public class Animation {

    private String name;
    private GreenfootImage[] frames;

    public Animation(String name, GreenfootImage[] frames) {
        this.name = name;
        this.frames = frames;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return frames.length;
    }

    public GreenfootImage getFrame(int index) {
        return frames[index];
    }
}
