import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public MyWorld()
    {    
        super(1536, 1024, 1);

        hoofdpersoon player = new hoofdpersoon(); 
        addObject(player, getWidth() / 2, getHeight() / 2);

        addObject(new SlimeBlock(), getWidth() / 2, getHeight() / 8 * 5 + 20);
    }

    public void act() {
        showHitboxes();
    }

    private void showHitboxes() {
        removeObjects(getObjects(DebugBox.class));

        if (!hoofdpersoon.debugHitbox) return;

        for (Block b : getObjects(Block.class)) {
            int w = b.getImage().getWidth();
            int h = b.getImage().getHeight();

            DebugBox box = new DebugBox(w, h);
            addObject(box, b.getX(), b.getY());
        }
    }
}
