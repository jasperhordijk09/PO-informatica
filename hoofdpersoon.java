import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Hoofdpersoon extends Personages {

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

    public Hoofdpersoon() {
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
        handleJumping();
        handleGravity();
        setImage(animator.update());
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            facingLeft = true;
            setLocation(getX() - 3, getY());
            animator.play("WalkingLeft");
        }
        else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) || !cantmove() {
            facingLeft = false;
            setLocation(getX() + 3, getY());
            animator.play("WalkingRight");
        }
        else {
            animator.play(facingLeft ? "IdleLeft" : "IdleRight");
        }
    }

    private void handleJumping() {
        if(onGround() && (Greenfoot.isKeyDown("space"))) {
            setLocation(getX(), getY() - 10); // Simulate a jump by moving the character up
        }
    }

    private void handleGravity() {
        if(!onGround()) {  
            setLocation(getX(), getY() + 5); // Simulate gravity by moving the character down
        }
    }
    
    private boolean onGround() {
        Block block = (Block) getOneIntersectingObject(Block.class);
        return block != null;
    }

    /** Return all Blocks this actor is currently intersecting. */
    private List<Block> getCollidingBlocks() {
        return getIntersectingObjects(Block.class);
    }

    /** Return (x,y) coords for all colliding blocks as a list of int[2]. */
    private List<int[]> getCollidingBlockCoords() {
        List<int[]> coords = new ArrayList<>();
        for (Block b : getCollidingBlocks()) {
            coords.add(new int[] { b.getX(), b.getY() });
        }
        return coords;
    }

    /**
     * Heuristic to determine which side of this actor is colliding with the block.
     * Returns "left", "right", "top" or "bottom".
     */
    private String collisionSide(Block b) {
        int px = getX();
        int py = getY();
        int bx = b.getX();
        int by = b.getY();

        int dx = px - bx; // positive -> player is to the right of block
        int dy = py - by; // positive -> player is below block

        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? "right" : "left";
        } else {
            return dy > 0 ? "bottom" : "top";
        }
    }

    /**
     * True if movement should be blocked horizontally (there is a left/right collision).
     */
    private boolean cantmove() {
        for (Block b : getCollidingBlocks()) {
            String side = collisionSide(b);
            if ("left".equals(side) || "right".equals(side)) {
                return true;
            }
        }
        return false;
    }

}
