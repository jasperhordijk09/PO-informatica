import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beagle here.
 * 
 * @author (your name) ddd
 * @version (a version number or a date)
 */
public class Beagle extends Personages
{
    /**
     * Act - do whatever the Beagle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        System.out.println("beagle: " + getX() + " " +  getY());
        Hoofdpersoon hp = (Hoofdpersoon) getWorld()
                .getObjects(Hoofdpersoon.class)
                .get(0);
        if (getX() > hp.getX()){
            setLocation(getX()-1, getY());
        }
        else if (getX() < hp.getX()){
            setLocation(getX()+1, getY());
            
        }
        if (getY() > hp.getY()){
            setLocation(getX(), getY()-1);
        }
        else if (getY() < hp.getY()){
            setLocation(getX(), getY()+1);
        }
    }
}
