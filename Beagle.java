import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import greenfoot.*;

public class Beagle extends Personages {

    private GreenfootImage[] walking_left = new GreenfootImage[11];
    private GreenfootImage idle;
    private double vy = 0;      // zwaartekracht snelheid
    private final int SPEED = 4;
    private final int GRAVITY = 1;

    public Beagle() {
        idle = new GreenfootImage("images/beagel_boys/image_0.png");
        setImage(idle);
        
        
    }

    public void act() {
        Hoofdpersoon hp = getWorld().getObjects(Hoofdpersoon.class).get(0);
        int diffX = hp.getX() - getX();

        // ---- X BEWEGING (links/rechts) ----
        int moveX = 0;
        if (Math.abs(diffX) > 5) {
            moveX = (diffX > 0) ? SPEED : -SPEED;
        }
        moveHorizontal(moveX);

        // ---- ZWAARTEKRACHT ----
        vy += GRAVITY;
        moveVertical((int) vy);
        if (hp.getX() > getX()) {
            for (int i = 0; i < 11; i++) {
            walking_left[i] = new GreenfootImage(
                "images/beagel_boys/image_" + (24 - i) + ".png"
            );
        }
        } else if (hp.getX() < getX()) {
            for (int i = 0; i < 11; i++) {
            walking_left[i] = new GreenfootImage(
                "images/beagel_boys/image_" + (24 - i) + "_mirror.png"
            );
            }
        }
        setImage(walking_left[(getX() / 20) % walking_left.length]);
    }

    // ================= X COLLISION =================
    private void moveHorizontal(int dx) {
        if (dx == 0) return;

        int halfW = getImage().getWidth() / 2;
        int offsetX = (dx > 0) ? halfW : -halfW;

        if (getOneObjectAtOffset(offsetX + dx, 0, Block.class) == null) {
            setLocation(getX() + dx, getY());
        }
    }

    // ================= Y COLLISION =================
    private void moveVertical(int dy) {
        if (dy == 0) return;

        int halfH = getImage().getHeight() / 2;
        int offsetY = (dy > 0) ? halfH : -halfH;

        if (getOneObjectAtOffset(0, offsetY + dy, Block.class) == null) {
            setLocation(getX(), getY() + dy);
        } else {
            // Stop vallen of omhoog gaan tegen plafond
            vy = 0;
        }
    }
}
