import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class achtergrond_centrumStad here.
 * 
3* @author (your name) 
 * @version (a version number or a date)
 */
public class achtergrond_centrumStad extends Achtergronden
{
    private int lastPlayerX = Integer.MIN_VALUE;
    /**
     * Act - do whatever the achtergrond_centrumStad wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getWorld() == null) return; //stopt als er geen wereld is

        List<Hoofdpersoon> players = getWorld().getObjects(Hoofdpersoon.class); //haalt alle objecten van het type Hoofdpersoon op
        if (players.isEmpty()) return;

        Hoofdpersoon p = players.get(0); //neemt de eerste hoofdpersoon (als er meerdere zijn, negeert het de rest)
        int px = p.getX();

        if (lastPlayerX == Integer.MIN_VALUE) { //als lastPlayerX nog niet is ingesteld, stel het in op de huidige x-positie van de speler en stop met acteren
            lastPlayerX = px;
            return;
        }

        int dx = px - lastPlayerX; //bereken het verschil in x-positie van de speler sinds de laatste keer dat act werd uitgevoerd

        //inverteren de player x zodat de afbeelding naar links beweegt als de hoofdpersoon naar rechts loopt
        setLocation(getX() - dx, getY());

        lastPlayerX = px;
    }
}
                    