import greenfoot.*;
import java.lang.*; 
public class hoofdpersoon extends Actor
{  
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    public enum walkingstate {
        IDLE,
        WALKING
    }
    public enum walkingdirection {
        LEFT,
        RIGHT
    }
    public String[] WALKING;
    public String[] IDLE;
    public String[] animationnames;
    public int[] animationlength;
    
    String[] animationnames = {
        "Walking",
        "Idle"
    };
    int[] animationlength = {
        12,
        1
    }; 
    String[] IDLE = {
        "sprite_001.png"
    };
    String[] WALKING = {
        "sprite_002.png",    "sprite_004.png",
        "sprite_006.png",    "sprite_010.png",
        "sprite_011.png",    "sprite_008.png",
        "sprite_003.png",    "sprite_005.png",
        "sprite_007.png",    "sprite_012.png",
        "sprite_013.png",    "sprite_009.png",
    };
    String LEFT = "/images/gyro_images/left/";
    String RIGHT = "/images/gyro_images/right/";
    
    {
        for (int idx = 0; idx < animationnames.length; idx++) {
            if (animationnames[idx].equals("Walking")) {
                for (int i = 0; i < animationlength[idx]; i++) {
                    WALKING[i] = LEFT + WALKING[i];
                }
                break;
            }
        }
        
        
    }

    public void act() {
        // Add act logic here
    }

    public void setsprite(){
        if (greenfoot.isKeyDown("a")) {
            faceLeft();
        } else if (greenfoot.isKeyDown("d")) {
            faceRight();
        }
        

        
    }
    public void movesprite(char direction){
        if (direction == 'a'){
            
        }
    }
}
