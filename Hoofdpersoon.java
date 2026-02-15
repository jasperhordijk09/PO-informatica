import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Hoofdpersoon extends Personages {

    private AnimationManager manager;
    private AnimationAnimator animator;
    private boolean facingLeft = false;

    double gravitatieconstante = 2.50; 
    private double movementSpeed = 2.0; 
    private double airMovementMultiplier = 2.0;

    double mass = 1.0;
    double jumpStrength = 80; 
    private boolean jumpHolding = false;
    private int jumpHoldMaxFrames = 10; // hoeveel frames extra omhoog houden
    private int jumpHoldTimer = 0;
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
        System.out.println(getX() + " " + verticalkracht);
        handleMovement();
        handleJumping();
        handleGravity();
        setImage(animator.update());
        ifgameover();
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//
    private void ifgameover() {
        if (getY() == (1024 - getHeight())) {
            if (level_selector.creditsSound != null) {
                level_selector.creditsSound.pause();
            }
            Greenfoot.playSound("sounds/gameover.mp3");
            Greenfoot.delay(50);
            Greenfoot.setWorld(new wereld_gameover());
            return;
    }
        }
    }
    private void handleMovement() {
        double speed = movementSpeed * (onGround() ? 1.0 : airMovementMultiplier);

        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            facingLeft = true;
            setLocation(getX() - (int)Math.round(speed), getY());
            animator.play("WalkingLeft");
        }
        else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            facingLeft = false;
            setLocation(getX() + (int)Math.round(speed), getY());
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
            // start jump-hold
            jumpHolding = true;
            jumpHoldTimer = 0;
        }
        // If player releases the jump key early, stop holding
        if (!(Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up"))) {
            jumpHolding = false;
        }
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private void handleGravity() {
        // Als speler de sprongknop ingehouden houdt en we zijn in de eerste jumpHold-frames,
        // pas verminderde gravity toe zodat de sprong langer duurt en hoger wordt.
        if (jumpHolding && jumpHoldTimer < jumpHoldMaxFrames && verticalkracht < 0) {
            // kleine negatieve aanpassing om extra hoogte te geven
            verticalkracht += gravitatieconstante * 0.22; // langzamere daling gedurende hold
            jumpHoldTimer++;
        } else {
            verticalkracht += gravitatieconstante;
            // zodra we geen hold meer hebben, reset timer
            if (!jumpHolding) jumpHoldTimer = 0;
        }

        if (verticalkracht > 12) verticalkracht = 12;

        if (onGround() && verticalkracht > 0) {
            verticalkracht = 0;
            // reset houd-status bij landen
            jumpHolding = false;
            jumpHoldTimer = 0;
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
        
        // Zelf grootte en blok grootte
        int playerWidth = getImage().getWidth();
        int playerHeight = getImage().getHeight();
        int blockWidth = b.getImage().getWidth();
        int blockHeight = b.getImage().getHeight();
        
        // Bereken de overlap aan elke kant
        int overlapLeft = (px + playerWidth / 2) - (bx - blockWidth / 2);
        int overlapRight = (bx + blockWidth / 2) - (px - playerWidth / 2);
        int overlapTop = (py + playerHeight / 2) - (by - blockHeight / 2);
        int overlapBottom = (by + blockHeight / 2) - (py - playerHeight / 2);
        
        // Bepaal welke overlap het kleinst is (dat is de zijde waar collision gebeurt)
        int minOverlap = Math.min(Math.min(overlapLeft, overlapRight), Math.min(overlapTop, overlapBottom));
        
        if (minOverlap == overlapTop) {
            return "top";
        } else if (minOverlap == overlapBottom) {
            return "bottom";
        } else if (minOverlap == overlapLeft) {
            return "left";
        } else {
            return "right";
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
