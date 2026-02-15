import greenfoot.*;

public class wereld_industrie extends World {

    private GreenfootImage[] backgrounds;
    private int[] bgX;
    private int imgWidth = 2432;
    private int imgHeight = 1024;
    private double cameraOffsetX = 0;
    private double parallaxFactor = 1.0; // snelheid van achtergrond ten opzichte van camera (1.0 = samen met camera, <1.0 = langzamer, >1.0 = sneller)
    private double blockParallaxFactor = 1.0; // pas dit aan voor blok parallax (1.0 = samen met achtergrond)
    private String currentname = "industrie"; // zet dit naar de naam van de wereld voor makkelijkere veranderen naar een nieuwe wereld
    private int hoogtespawnplayer = 500; // zet dit naar de gewenste hoogte waarop de player spawnt
    private boolean blocksInitialized = false; // zorgt ervoor dat startblokken maar één keer worden toegevoegd
    private java.util.Set<Integer> spawnedBlocks = new java.util.HashSet<>(); // houdt bij welke blokken al gespawnd zijn
    private int blockSpawnX = 600; // X-waarde waar blokken spawnen (pas dit aan)
    private int blockOffsetX = 250; // Hoeveel pixels vóór de player blokken spawnen

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    public wereld_industrie() {
        super(1536, 1024, 1, false);

        backgrounds = new GreenfootImage[] {
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-1.png"),
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-2.png"),
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-3.png"),
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-4.png")
        };
        
        Hoofdpersoon player = new Hoofdpersoon(); 
        addObject(player, 200, getHeight() - hoogtespawnplayer);

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
            b.setLocation(b.getX() - (int) (deltaX * blockParallaxFactor), b.getY());
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
        java.util.List<Hoofdpersoon> players = getObjects(Hoofdpersoon.class);
        if (players == null || players.isEmpty()) return;
        Hoofdpersoon player = players.get(0);
        
        //============================================================================================================//
        // Startblokken - worden maar één keer toegevoegd
        if (!blocksInitialized) {
            addBlockAtPosition(275, getHeight() - 300, new SlimeBlock());
            spawnedBlocks.add(275);
            blocksInitialized = true;
        }
    
        //=====================voorbeelden van blokken================================================================//
        // Blokken spawnen wanneer de player langs blockSpawnX loopt
        // Ze verschijnen blockOffsetX pixels vóór de player
        //spawnBlockWhenPlayerReachesX(200, new SlimeBlock(), getHeight() - 300, (blockOffsetX + 250) - 200);
        //spawnBlockWhenPlayerReachesX(201 , new GrassBlock(), getHeight() - 360, (blockOffsetX + 500) - 201);
        //spawnBlockWhenPlayerReachesX(202 , new StoneBlock(), getHeight() - 360, (blockOffsetX + 900) - 202);

        //============================================================================================================//
    }
    
    private void addBlockAtPosition(int x, int y, Block block) {
        addObject(block, x, y);
    }
    
    private void spawnBlockWhenPlayerReachesX(int spawnX, Block block, int y, int offsetX) {
        java.util.List<Hoofdpersoon> players = getObjects(Hoofdpersoon.class);
        if (players == null || players.isEmpty()) return;
        
        Hoofdpersoon player = players.get(0);
        
        // Check of blok nog niet is gespawnd en player x-waarde heeft bereikt
        if (!spawnedBlocks.contains(spawnX) && player.getX() >= spawnX) {
            addBlockAtPosition(spawnX + offsetX, y, block);
            spawnedBlocks.add(spawnX);
        }
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//
}