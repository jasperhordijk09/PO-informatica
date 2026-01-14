import greenfoot.*;
import java.lang.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class hoofdpersoon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class hoofdpersoon extends Actor
{  
    /**
     * Act - do whatever the hoofdpersoon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
    */
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
    public string WALKING;
    public string IDLE;
    public string standingstill;
    public String animationlengt;
    public void act()
    animationlengt = {"2","4","6","8","10","12","14","16","18","20"}; 
    WALKING = {}
    
    {
        if (greenfoot.isKeyDown("w","a","s","d")){
            if (greenfoot.isKeyDown("w")){
                movesprite(w);
            }
            if (greenfoot.isKeyDown("a")){
                movesprite(a);
            }
            if (greenfoot.isKeyDown("s")){
                movesprite(s);
            }
            if (greenfoot.isKeyDown("d")){
                movesprite(d);
            }
        }
        
        
    }
    public void faceLeft() {
    setRotation(180); // Face left
}

    public void faceRight() {
        setRotation(0); // Face right
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
