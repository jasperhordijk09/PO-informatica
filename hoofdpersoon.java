import greenfoot.*;

public class hoofdpersoon extends Actor {

    private AnimationManager manager;
    private AnimationAnimator animator;

    private boolean facingLeft = false;

    private double vSpeed = 0;
    private double gravity = 0.5;
    private int jumpStrength = -10;

    private double hSpeed = 0;
    private double moveSpeed = 0.7;

    private int maxStepHeight = 3;

    private String[] WALK_ORDER = {
        "sprite_002.png","sprite_003.png","sprite_004.png","sprite_005.png",
        "sprite_006.png","sprite_007.png","sprite_008.png","sprite_009.png",
        "sprite_010.png","sprite_011.png","sprite_012.png","sprite_013.png"
    };

    private String[] IDLE_ORDER = { "sprite_001.png" };

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
        handleJump();
        handleMovement();
        applyGravity();
        applyFriction();
        limitspeed();
        setImage(animator.update());
    }

    private int getInputDirection() {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) return -1;
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) return 1;
        return 0;
    }

    public void limitspeed() {
        Block block = (Block)getOneObjectAtOffset(0, getImage().getHeight()/2, Block.class);
        if (block instanceof SlimeBlock){
            if (hSpeed > 2.9) hSpeed = 3;
            if (hSpeed < -2.9) hSpeed = -3;
        } else {
            if (hSpeed < -4.9) hSpeed = -5;
            if (hSpeed > 4.9) hSpeed = 5;
        }
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            facingLeft = true;
            hSpeed -= moveSpeed;
            animator.play("WalkingLeft");
        }
        else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            facingLeft = false;
            hSpeed += moveSpeed;
            animator.play("WalkingRight");
        }
        else {
            animator.play(facingLeft ? "IdleLeft" : "IdleRight");
        }

        setLocation(getX() + (int)hSpeed, getY());
    }

    private void handleJump() {
        if (Greenfoot.isKeyDown("space") && onGround()) {
            vSpeed = jumpStrength;
        }
    }

    private void applyGravity() {
        vSpeed += gravity;
        setLocation(getX(), (int)(getY() + vSpeed));

        Block blockBelow = (Block)getOneObjectAtOffset(0, getImage().getHeight()/2, Block.class);

        if (blockBelow != null) {
            int playerBottom = getY() + getImage().getHeight() / 2;
            int blockTop = blockBelow.getY() - blockBelow.getImage().getHeight() / 2;

            if (playerBottom >= blockTop && vSpeed >= 0) {
                setLocation(getX(), blockTop - getImage().getHeight() / 2);
                vSpeed = 0;
            }
        }
    }

    private void applyFriction() {
        Block block = (Block)getOneObjectAtOffset(0, getImage().getHeight()/2, Block.class);

        double friction = 0.5;
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            if(block instanceof SlimeBlock) friction = 1.2;
            else if(block instanceof IceBlock) friction = 0.8;
            else friction = 1;
        }
        else {
            if(block instanceof SlimeBlock) friction = 1.4;
            else if(block instanceof IceBlock) friction = 1.2;
            else friction = 1.3;
        }

        hSpeed /= friction;

        if (Math.abs(hSpeed) < 0.1) hSpeed = 0;
    }

    private boolean onGround() {
        setLocation(getX(), getY() + 1);
        boolean touching = isTouching(Block.class);
        setLocation(getX(), getY() - 1);
        return touching;
    }
}
