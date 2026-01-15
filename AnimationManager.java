import greenfoot.*;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager {

    private Map<String, Animations> animations = new HashMap<>();

    public AnimationManager() {
    }

    public Map<String, Animations> getAnimations() {
        return animations;
    }

    /**
     * Load animation using a manual file order.
     * Example:
     * loadAnimationManual("WalkLeft", "images/gyro_images/left", WALK_ORDER);
     */
    public void loadAnimationManual(String name, String folderPath, String[] fileOrder) {
        GreenfootImage[] frames = new GreenfootImage[fileOrder.length];

        for (int i = 0; i < fileOrder.length; i++) {
            frames[i] = new GreenfootImage(folderPath + "/" + fileOrder[i]);
        }

        animations.put(name, new Animation(name, frames));
    }

    /**
     * Check if an animation exists.
     */
    public boolean hasAnimation(String name) {
        return animations.containsKey(name);
    }

    /**
     * Get animation by name.
     */
    public Animations get(String name) {
        return animations.get(name);
    }
}
