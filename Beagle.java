 import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import greenfoot.*;

public class Beagle extends Personages {

    private GreenfootImage[] walking_left = new GreenfootImage[11];
    private GreenfootImage idle;
    private double vy = 0;      // zwaartekracht snelheid
    private final int SPEED = 4;
    private final int GRAVITY = 1;

    public Beagle() {
        //zorgt dat hij een uiterlijk heeft aan het begin
        idle = new GreenfootImage("images/beagel_boys/image_0.png");
        setImage(idle);
        
        
    }

    public void act() {
        //bekijkt de coordinaten van de hoofdpersoon en bekijkt hoe ver die van hem is
        Hoofdpersoon hp = getWorld().getObjects(Hoofdpersoon.class).get(0);
        int diffX = hp.getX() - getX();

        // X beweging(links/rechts)
        int moveX = 0;
        if (Math.abs(diffX) > 5) {
            moveX = (diffX > 0) ? SPEED : -SPEED;
        }
        moveHorizontal(moveX);

        // zwaartekracht
        vy += GRAVITY;
        moveVertical((int) vy);
        
        // hierbij gebruikt hij alle links kijkende images als hij recht van hoofdpersoon is
        if (hp.getX() > getX()) {
            for (int i = 0; i < 11; i++) {
            walking_left[i] = new GreenfootImage(
                "images/beagel_boys/image_" + (24 - i) + ".png"
            );
        }
        // en alle rechts kijkkende als hij links is
        } else if (hp.getX() < getX()) {
            for (int i = 0; i < 11; i++) {
            walking_left[i] = new GreenfootImage(
                "images/beagel_boys/image_" + (24 - i) + "_mirror.png"
            );
            }
        }
        setImage(walking_left[(getX() / 20) % walking_left.length]);
        // Alleen triggeren wanneer Beagle's zijkant het midden van Hoofdpersoon raakt
        int beagleHalfW = getImage().getWidth() / 2;
        int beagleLeft = getX() - beagleHalfW;
        int beagleRight = getX() + beagleHalfW;
        int hpCenterX = hp.getX();

        int beagleHalfH = getImage().getHeight() / 2;
        int hpHalfH = hp.getImage().getHeight() / 2;
        boolean verticalOverlap = Math.abs(getY() - hp.getY()) <= (beagleHalfH + hpHalfH);
        
        // als beagel wilie van dichtbij aanraakt dan is het gameover
        if (verticalOverlap) {
            if (beagleRight >= hpCenterX && getX() < hpCenterX) {
                gameover();
            } else if (beagleLeft <= hpCenterX && getX() > hpCenterX) {
                gameover();
            }
        }
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
    // als het game over is dan speel hij het geluidje af en gaat naar dat scherm
    public void gameover() {
        Greenfoot.playSound("sounds/gameover.mp3");
        Greenfoot.delay(50);
        Greenfoot.setWorld(new wereld_gameover());
        return;
    }
}
