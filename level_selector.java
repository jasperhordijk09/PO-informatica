import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class level_selector extends World
{

    public level_selector()
    {    
        // Create a new world with 1536x1024 cells with a cell size of 1x1 pixels.
        super(1536, 1024, 1); 
        // voegt alle icoontjes toe van alle levels
        addObject(new level_centrum(), 820, 310);
        addObject(new level_fabriek(), 1250, 560);
        addObject(new level_industrie(), 1100, 815);
        addObject(new level_pakhuis(), 1100, 160);
        addObject(new level_park(), 375, 535);
        addObject(new level_werkplaats(), 137, 200);

    }
}
