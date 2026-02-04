import greenfoot.*;

public class wereld_park extends World {

    private GreenfootImage[] backgrounds;
    private int[] bgX;
    private int scrollSpeed = 4;
    private int imgWidth = 1024;
    private int imgHeight = 1024;

    public wereld_park() {
        super(1536, 1024, 1, false);

        backgrounds = new GreenfootImage[] {
            new GreenfootImage("park-bg/park-img-1.png"),
            new GreenfootImage("park-bg/park-img-2.png"),
            new GreenfootImage("park-bg/park-img-3.png"),
            new GreenfootImage("park-bg/park-img-4.png")
        };

        bgX = new int[backgrounds.length];

        for (int i = 0; i < backgrounds.length; i++) {
            bgX[i] = i * imgWidth;
        }

        setBackground(new GreenfootImage(getWidth(), getHeight()));
    }

    public void act() {
        scrollBackgrounds();
        drawBackgrounds();
    }

    private void scrollBackgrounds() {
        for (int i = 0; i < bgX.length; i++) {
            bgX[i] -= scrollSpeed;

            if (bgX[i] <= -imgWidth) {
                bgX[i] += imgWidth * backgrounds.length;
            }
        }
    }

    private void drawBackgrounds() {
        GreenfootImage worldBG = getBackground();
        worldBG.clear();

        for (int i = 0; i < backgrounds.length; i++) {
            worldBG.drawImage(backgrounds[i], bgX[i], 0);
        }
    }
}
