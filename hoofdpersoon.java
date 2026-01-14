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
    public String animationlengt;
    public void act()
    {
        if (greenfoot.isKeyDown("w","a","s","d")){
            movesprite();
        }
        
        
    }

    public void setsprite(){
        

        
    }
    public void movesprite(){
        if (greenfoot.isKeyDown("w")){
            setLocation(getX(), getY()-1);
            setsprite();
        }
        if (greenfoot.isKeyDown("a")){
            setLocation(getX()-1, getY());
            setsprite();
        }
        if (greenfoot.isKeyDown("d")){
            if (greenfoot.isKeyDown("shift")){
                setLocation(getX()+3, getY());
                setsprite(2);
            } else {
                setLocation(getX()+1, getY());
                setsprite(1);

            }
        }
    }
}
