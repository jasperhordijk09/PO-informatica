import greenfoot.*;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager {

    private Map<String, Animation> animations = new HashMap<>();

    public Map<String, Animation> getAnimations() {
        return animations;
    }

    public void loadAnimationManual(String name, String folderPath, String[] fileOrder) {
        GreenfootImage[] frames = new GreenfootImage[fileOrder.length];

        for (int i = 0; i < fileOrder.length; i++) {
            frames[i] = new GreenfootImage(folderPath + "/" + fileOrder[i]);
        }

        animations.put(name, new Animation(name, frames));
    }

    public Animation get(String name) {
        return animations.get(name);
    }
}
