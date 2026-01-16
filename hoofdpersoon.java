import greenfoot.*;

public class hoofdpersoon extends Actor {

    private AnimationManager manager;
    private AnimationAnimator animator;

    private boolean facingLeft = false;

    private double vSpeed = 0;
    private double gravity = 0.5;
    private int jumpStrength = -10;

    private double hSpeed = 0; // horizontal speed
    private double moveSpeed = 0.6; // acceleration

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
        applyGravity();
        handleMovement();
        handleJump();
        applyFriction();
        setImage(animator.update());
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

        setLocation((int)(getX() + hSpeed), getY());

        while (isTouching(Block.class)) {
            setLocation(getX() - (int)Math.signum(hSpeed), getY());
            hSpeed = 0;
        }
    }

    private void handleJump() {
        if (Greenfoot.isKeyDown("space") && onGround()) {
            vSpeed = jumpStrength;
        }
    }

    private void applyGravity() {
        vSpeed += gravity;
        setLocation(getX(), (int)(getY() + vSpeed));

        while (isTouching(Block.class)) {
            setLocation(getX(), getY() - 1);
            vSpeed = 0;
        }
    }

    private void applyFriction() {
        Block block = (Block)getOneObjectAtOffset(0, getImage().getHeight()/2, Block.class);

        double friction = 1.0;

        if (block != null) {
            friction = block.getFriction();
        }

        hSpeed /= friction;

        if (Math.abs(hSpeed) < 0.1) {
            hSpeed = 0;
        }
    }

    private boolean onGround() {
        setLocation(getX(), getY() + 1);
        boolean touching = isTouching(Block.class);
        setLocation(getX(), getY() - 1);
        return touching;
    }
}
