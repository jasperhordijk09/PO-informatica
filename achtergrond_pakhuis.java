import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class achtergrond_pakhuis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class achtergrond_pakhuis extends Achtergronden

{
    private int lastPlayerX = Integer.MIN_VALUE;
    /**
     * Act - do whatever the achtergrond_pakhuis wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getWorld() == null) return;

        List<Hoofdpersoon> players = getWorld().getObjects(Hoofdpersoon.class);
        if (players.isEmpty()) return;

        Hoofdpersoon p = players.get(0);
        int px = p.getX();

        if (lastPlayerX == Integer.MIN_VALUE) {
            lastPlayerX = px;
            return;
        }

        int dx = px - lastPlayerX;
        setLocation(getX() - dx, getY());

        lastPlayerX = px;
    }
}
