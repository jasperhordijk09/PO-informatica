import greenfoot.*;
import java.util.Map;

public class AnimationAnimator {

    private Map<String, Animation> animations;
    private Animation current;
    private int frameIndex = 0;
    private int delay;
    private int counter = 0;

    public AnimationAnimator(Map<String, Animation> animations, int delay) {
        this.animations = animations;
        this.delay = delay;
    }

    public void play(String name) {
        Animation next = animations.get(name);
        if (next == null) return;

        if (current != next) {
            current = next;
            frameIndex = 0;
            counter = 0;
        }
    }

    public GreenfootImage update() {
        if (current == null) return null;

        counter++;
        if (counter >= delay) {
            counter = 0;
            frameIndex = (frameIndex + 1) % current.getLength();
        }

        return current.getFrame(frameIndex);
    }
}
