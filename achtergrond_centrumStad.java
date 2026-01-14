import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class achtergrond_centrumStad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class achtergrond_centrumStad extends Actor
{
    /**
     * Act - do whatever the achtergrond_centrumStad wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        move(-1);
        if(getX() == 1024 / 8 * 3 ){
            setLocation(1024, getY());
        }
    }
}
