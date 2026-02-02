import greenfoot.*;

public class DebugBox extends Actor {

    public DebugBox(int w, int h) {
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(Color.RED);
        img.drawRect(0, 0, w - 1, h - 1);
        setImage(img);
    }
}
