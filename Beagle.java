import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beagle here.
 * 
 * @author (your name) ddd
 * @version (a version number or a date)
 */
public class Beagle extends Personages {
    /**
     * Act - do whatever the Beagle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage[] walking_left = new GreenfootImage[11];
    private GreenfootImage idle;

    public Beagle() {
        idle = new GreenfootImage("images/beagel_boys/image_0.png");
        setImage(idle);
        for (int i = 0; i < 11; i++) {
            walking_left[i] = new GreenfootImage("images/beagel_boys/image_" + (24 - i) + "_mirror.png");
        }
    }

    public void act() {
        Hoofdpersoon hp = (Hoofdpersoon) getWorld()
                .getObjects(Hoofdpersoon.class)
                .get(0);
        int diffX = hp.getX() - getX();
        int diffY = hp.getY() - getY() +20;
        int distance = (int) Math.sqrt(diffX * diffX + diffY * diffY);
        if (distance < 4) {
            setLocation(hp.getX(), hp.getY()+20);
        } else {
            double scale = 4.0 / distance; // Adjust the speed of the Beagle int moveX = (int) (diffX * scale); int moveY = (int) (diffY * scale); setLocation(getX() - moveX, getY() - moveY);
            setLocation((int)(diffX * scale)+getX(), (int)(diffY * scale)+getY());
        } 
        setImage(walking_left[(getX() / 20) % walking_left.length]);
    }
}
