import greenfoot.*;
import java.lang.*; 
public class hoofdpersoon extends Actor
{  
    String[] WALKING;
    String[] IDLE;
    String[] animationnames;
    int[] animationlength;
    String[] facingdirection;
    String[] animationnames;
    int[] animationlength;
    public setup(){

        facingdirection = { "/images/gyro_images/left/" , "/images/gyro_images/right/" };
        animationnames = { "Walking" , "Idle" };
        animationlength = { 12 , 1 }; 
        IDLE = {"sprite_001.png"};
        WALKING = {
            "sprite_002.png",    "sprite_004.png",
            "sprite_006.png",    "sprite_010.png",
            "sprite_011.png",    "sprite_008.png",
            "sprite_003.png",    "sprite_005.png",
            "sprite_007.png",    "sprite_012.png",
            "sprite_013.png",    "sprite_009.png",
            };
    }
    public void move() {
        For (int i = 0; i < animationnames.length; i++) 
        {
            if (animationnames[idx].equals("Walking")) {
                for (int i = 0; i < animationlength[idx]; i++) 
                {
                
                }
                break;
            }
        }
    }
    

    public void act() 
        {
            
        }   

    public void set_direction()
    {
        if (greenfoot.isKeyDown("a","left"))
        {
            facingdirection = LEFT;
        }
        else
        {
            facingdirection = RIGHT;
        }     
    }
}
