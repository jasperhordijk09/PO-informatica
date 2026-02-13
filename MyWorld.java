import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public MyWorld()
    {    
        super(1536, 1024, 1);

        Hoofdpersoon player = new Hoofdpersoon(); 
        addObject(player, getWidth() / 2, getHeight() / 2);

        addObject(new SlimeBlock(), getWidth() / 2, getHeight() / 8 * 5 + 10);
    }


}
