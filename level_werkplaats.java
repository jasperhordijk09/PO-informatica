import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class level_werkplaats here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class level_werkplaats extends levels_selector
{
    /**
     * Act - do whatever the level_werkplaats wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mousePressed(this)) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
