import greenfoot.*;

public class hoofdpersoon extends Actor {

    private AnimationManager manager;
    private AnimationAnimator animator;
    private boolean facingLeft = false;

    private String[] WALK_ORDER = {
        "sprite_002.png",
        "sprite_003.png",
        "sprite_004.png",
        "sprite_005.png",
        "sprite_006.png",
        "sprite_007.png",
        "sprite_008.png",
        "sprite_009.png",
        "sprite_010.png",
        "sprite_011.png",
        "sprite_012.png",
        "sprite_013.png",
    };

    private String[] IDLE_ORDER = {
        "sprite_001.png"
    };

    public hoofdpersoon() {
        manager = new AnimationManager();

        manager.loadAnimationManual("IdleLeft",  "images/gyro_images/left",  IDLE_ORDER);
        manager.loadAnimationManual("IdleRight", "images/gyro_images/right", IDLE_ORDER);

        manager.loadAnimationManual("WalkingLeft",  "images/gyro_images/left",  WALK_ORDER);
        manager.loadAnimationManual("WalkingRight", "images/gyro_images/right", WALK_ORDER);

        animator = new AnimationAnimator(manager.getAnimations(), 5);

        animator.play("IdleRight");
        setImage(animator.update());
    }

    public void act() {
        handleMovement();
        setImage(animator.update());
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            facingLeft = true;
            setLocation(getX() - 3, getY());
            animator.play("WalkingLeft");
        }
        else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            facingLeft = false;
            setLocation(getX() + 3, getY());
            animator.play("WalkingRight");
        }
        else {
            animator.play(facingLeft ? "IdleLeft" : "IdleRight");
        }
    }
}
