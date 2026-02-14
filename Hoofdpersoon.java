import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Hoofdpersoon extends Personages {

    private AnimationManager manager;
    private AnimationAnimator animator;
    private boolean facingLeft = false;

    double gravitatieconstante = 3.00;

    double mass = 1.0;
    double jumpStrength = 100;
    double verticalkracht = 0;
    int beginY = 0;
    boolean hi;
    double targetPeakY = 0;

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

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//


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

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    public void act() {
        System.out.println(onGround() + " " + getCollidingBlockCoords());
        handleMovement();
        handleJumping();
        handleGravity();
        setImage(animator.update());
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

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

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private void handleJumping() {
        if ((Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) && onGround()) {
            beginY = getY();
            targetPeakY = beginY - (int)jumpStrength;
            verticalkracht = -Math.sqrt(2 * gravitatieconstante * jumpStrength);
        }
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private void handleGravity() {
        verticalkracht += gravitatieconstante;
        if (verticalkracht > 10) verticalkracht = 10;

        if (onGround()) {
            verticalkracht = 0;
        }
        
        setLocation(getX(), (int)Math.round(getY() + verticalkracht));

        
    }
    
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private boolean onGround() {
        Block block = (Block) getOneIntersectingObject(Block.class);
        if (block != null) {
            String side = collisionSide(block);
            if ("top".equals(side)) {
                hi = true;
            }
        } else {
            hi = false;
        }
        return hi;
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private List<Block> getCollidingBlocks() {
        return getIntersectingObjects(Block.class);
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private List<int[]> getCollidingBlockCoords() {
        List<int[]> coords = new ArrayList<>();
        for (Block b : getCollidingBlocks()) {
            coords.add(new int[] { b.getX(), b.getY() });
        }
        return coords;
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private String collisionSide(Block b) {
        int px = getX();
        int py = getY();
        int bx = b.getX();
        int by = b.getY();

        int dx = px - bx;
        int dy = py - by;

        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? "right" : "left";
        } else {
            return dy > 0 ? "bottom" : "top";
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private boolean cantmove() {
        for (Block b : getCollidingBlocks()) {
            String side = collisionSide(b);
            if ("left".equals(side) || "right".equals(side)) {
                return true;
            }
        }
        return false;
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//


//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

}
