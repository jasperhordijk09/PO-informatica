import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class wereld_leeg_test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class wereld_park extends World
{

    /**
     * Constructor for objects of class wereld_leeg_test.
     * 
     */
    public wereld_park()
    {    
        // Create a new world with 1536x1024 cells with a cell size of 1x1 pixels.
        super(1536, 1024, 1); 
        addObject(new achtergrond_park(), 1024, 512);
    }
}
