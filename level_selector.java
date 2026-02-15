import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class level_selector extends World
{
    public static GreenfootSound creditsSound;
    public static boolean comp = false; //park is klaar
    public static boolean comc = false; //centrum is klaar
    public static boolean comf = false; //fabriek is klaar
    public static boolean comi = false; //industrie is klaar
    public static boolean comh = false; //pakhuis is klaar
    public static boolean comw = false; //werkplaats is klaar
    public level_selector()
    {    
        // Create a new world with 1536x1024 cells with a cell size of 1x1 pixels.
        super(1536, 1024, 1); 
        // voegt alle icoontjes toe van alle levels
        addObject(new level_park(), 375, 535);
        addObject(new level_werkplaats(), 137, 200);
        System.out.println("comp = " + comp);
        if (comp == true) {
            addObject(new level_centrum(), 820, 310);
        }
        if (comc == true) {
            addObject(new level_industrie(), 1100, 815);
        }
        if (comi == true) {
            addObject(new level_fabriek(), 1250, 560);
        }
        if (comf == true) {
            addObject(new level_pakhuis(), 1100, 160);
        }
        
        // speel Credits audio en houd het in een statische variabele zodat andere
        // classes (bv. `Beagle`) het kunnen stoppen bij gameover
        if (creditsSound == null) {
            creditsSound = new GreenfootSound("sounds/Credits.mp3");
        }
        creditsSound.play();
    }
}
