import greenfoot.*;

public class wereld_park extends World {

    private GreenfootImage[] backgrounds;
    private int[] bgX;
    private int imgWidth = 1024;
    private int imgHeight = 1024;
    private double cameraOffsetX = 0;
    private double parallaxFactor = 1.0;

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    public wereld_park() {
        super(1536, 1024, 1, false);

        backgrounds = new GreenfootImage[] {
            new GreenfootImage("park-bg/park-img-1.png"),
            new GreenfootImage("park-bg/park-img-2.png"),
            new GreenfootImage("park-bg/park-img-3.png"),
            new GreenfootImage("park-bg/park-img-4.png")
        };
        
        Hoofdpersoon player = new Hoofdpersoon(); 
        addObject(player, getWidth() / 2, getHeight() / 2);

        addObject(new SlimeBlock(), getWidth() / 2, getHeight() / 8 * 5 + 10);

        bgX = new int[backgrounds.length];

        for (int i = 0; i < backgrounds.length; i++) {
            bgX[i] = i * imgWidth;
        }

        setBackground(new GreenfootImage(getWidth(), getHeight()));
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    public void act() {
        scrollBackgroundsWithPlayer();
        renderblocks();
        drawBackgrounds();
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private void scrollBackgroundsWithPlayer() {
        java.util.List<Hoofdpersoon> players = getObjects(Hoofdpersoon.class);
        if (players == null || players.isEmpty()) return;
        
        Hoofdpersoon player = players.get(0);
        double playerX = player.getX();
        double worldCenterX = getWidth() / 2.0;
        double targetCameraX = playerX - worldCenterX;
        double cameraDeltaX = targetCameraX - cameraOffsetX;
        cameraOffsetX = targetCameraX;

        double bgDeltaX = cameraDeltaX;

        for (int i = 0; i < bgX.length; i++) {
            bgX[i] -= (int) bgDeltaX;
            
            int totalWidth = imgWidth * backgrounds.length;
            bgX[i] = ((bgX[i] % totalWidth) + totalWidth) % totalWidth;
        }
        
        moveBlocksWithCamera(bgDeltaX);
    }
    
    private void moveBlocksWithCamera(double deltaX) {
        for (Block b : getObjects(Block.class)) {
            b.setLocation(b.getX() - (int) deltaX, b.getY());
        }
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private void drawBackgrounds() {
        GreenfootImage worldBG = getBackground();
        worldBG.clear();

        for (int i = 0; i < backgrounds.length; i++) {

            worldBG.drawImage(backgrounds[i], bgX[i], 0);
            
            worldBG.drawImage(backgrounds[i], bgX[i] - imgWidth * backgrounds.length, 0);
            worldBG.drawImage(backgrounds[i], bgX[i] + imgWidth * backgrounds.length, 0);
        }
    }


//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private void renderblocks() {

        //============================================================================================================//
        
        java.util.List<Hoofdpersoon> players = getObjects(Hoofdpersoon.class);
        if (players == null || players.isEmpty()) return;
        Hoofdpersoon player = players.get(0);
        double playerX = player.getX();
        
        //============================================================================================================//
        
        if ((playerX > (getWidth() / 2 + 20)) || (playerX < (getWidth() / 2 + 30))) {
            Block block1 = new SlimeBlock();
            addObject(block1, getWidth() / 2, getHeight() / 8 * 5 + 10);
        }

        //============================================================================================================//
    }

}

/**
if (player.getX() > "de x waarde van de player wanner het blok geplaatst moet worden") {
            Block "naam van block (mag niet herhalen)" = new SlimeBlock();
            addObject(block1, getWidth(), "hoogte van het block");
        }  
*/     
