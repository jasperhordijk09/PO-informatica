public class hoofdpersoon extends Actor {

    private AnimationManager anim;
    private boolean facingLeft = false;
    public hoofdpersoon() {
        anim = new AnimationManager(5);

        anim.loadAnimationManual("WalkingLeft",  "images/gyro_images/left",  WALK_LEFT);
        anim.loadAnimationManual("WalkingRight", "images/gyro_images/right", WALK_RIGHT);

        anim.setAnimation("WalkingRight");
        setImage(anim.update());
    }


    // You control the order here
    private String[] WALK_LEFT = {
        "sprite_004.png",
        "sprite_002.png",
        "sprite_010.png",
        "sprite_003.png",
        "sprite_001.png",
        "sprite_007.png",
        "sprite_006.png",
        "sprite_009.png",
        "sprite_005.png",
        "sprite_008.png",
        "sprite_011.png",
        "sprite_013.png"
    };

    private String[] WALK_RIGHT = WALK_LEFT; // same order, different folder

    public hoofdpersoon() {
        anim = new AnimationManager(5);

        anim.loadAnimationManual("WalkingLeft",  "images/gyro_images/left",  WALK_LEFT);
        anim.loadAnimationManual("WalkingRight", "images/gyro_images/right", WALK_RIGHT);

        anim.setAnimation("WalkingRight");
        setImage(anim.update());
    }

    public void act() {
        handleMovement();
        updateAnimation();
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            facingLeft = true;
            setLocation(getX() - 3, getY());
            anim.setAnimation("WalkingLeft");
        }
        else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            facingLeft = false;
            setLocation(getX() + 3, getY());
            anim.setAnimation("WalkingRight");
        }
    }

    private void updateAnimation() {
        GreenfootImage frame = anim.update();
        if (frame != null) setImage(frame);
    }
}
