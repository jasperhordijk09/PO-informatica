    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class In2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class In2 extends Inventions
{
    /**
     * Act - do whatever the In2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Check if Hoofdpersoon (Willie) touches the middle of this object
        Hoofdpersoon hp = getWorld().getObjects(Hoofdpersoon.class).get(0);
        
        int thisHalfW = getImage().getWidth() / 2;
        int thisHalfH = getImage().getHeight() / 2;
        int hpHalfH = hp.getImage().getHeight() / 2;
        
        // Check if hp's center X is within this object's horizontal bounds
        int thisCenterX = getX();
        int hpCenterX = hp.getX();
        
        boolean horizontalOverlap = Math.abs(hpCenterX - thisCenterX) <= thisHalfW;
        boolean verticalOverlap = Math.abs(getY() - hp.getY()) <= (thisHalfH + hpHalfH);
        
        // If hp touches the middle of this object, execute comment
        if (horizontalOverlap && verticalOverlap) {
            level_selector.comc = true;
            if (level_selector.creditsSound != null) {
                level_selector.creditsSound.pause();
            }
            Greenfoot.playSound("sounds/complete.mp3");
            Greenfoot.delay(100);
            Greenfoot.setWorld(new level_selector());
        }
    }
}