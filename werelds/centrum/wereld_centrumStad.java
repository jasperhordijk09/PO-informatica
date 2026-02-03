import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class wereld_centrumStad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class wereld_centrumStad extends World
{

    /**
     * Constructor for objects of class wereld_centrumStad.
     * 
     */
    public wereld_centrumStad()
    {    
        // Create a new world with 1536x1024 cells with a cell size of 1x1 pixels.
        super(1536, 1024, 1); 
        addObject(new achtergrond_centrumStad(), 1536 / 2, 1024 / 2);
    }
}
