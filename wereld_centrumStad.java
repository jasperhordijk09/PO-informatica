import greenfoot.*;

public class wereld_centrumStad extends World {

    private GreenfootImage[] backgrounds;
    private int[] bgX;
    private int imgWidth = 2432;
    private int imgHeight = 1024;
    private double cameraOffsetX = 0;
    private double parallaxFactor = 1.0; // snelheid van achtergrond ten opzichte van camera (1.0 = samen met camera, <1.0 = langzamer, >1.0 = sneller)
    private double blockParallaxFactor = 2.0; // pas dit aan voor blok parallax (1.0 = samen met achtergrond)
    private String currentname = "centrum"; // zet dit naar de naam van de wereld voor makkelijkere veranderen naar een nieuwe wereld
    private int hoogtespawnplayer = 500; // zet dit naar de gewenste hoogte waarop de player spawnt
    private boolean blocksInitialized = false; // zorgt ervoor dat startblokken maar één keer worden toegevoegd
    private java.util.Set<Integer> spawnedBlocks = new java.util.HashSet<>(); // houdt bij welke blokken al gespawnd zijn
    private java.util.Set<Integer> visitedBlocks = new java.util.HashSet<>(); // houdt bij welke blokken de player heeft bereikt
    private int blockSpawnX = 600; // X-waarde waar blokken spawnen (pas dit aan)
    private int blockOffsetX = 312; // Hoeveel pixels vóór de player blokken spawnen

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    public wereld_centrumStad() {
        super(1536, 1024, 1, false);

        backgrounds = new GreenfootImage[] {
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-1.png"),
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-2.png"),
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-3.png"),
            new GreenfootImage(currentname + "-bg/" + currentname + "-img-4.png")
        };
        
        Hoofdpersoon player = new Hoofdpersoon(); 
        addObject(player, 768, getHeight() - hoogtespawnplayer);

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

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//
    
    private void moveBlocksWithCamera(double deltaX) {
        for (Block b : getObjects(Block.class)) {
            b.setLocation(b.getX() - (int) (deltaX * blockParallaxFactor), b.getY());
        }
        for (Inventions i : getObjects(Inventions.class)) {
            i.setLocation(i.getX() - (int) (deltaX * blockParallaxFactor), i.getY());
        }
        for (Beagle beagle : getObjects(Beagle.class)) {
            beagle.setLocation(beagle.getX() - (int) (deltaX), beagle.getY());
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
            addBlockAtPosition(768, getHeight() - 300, new StoneBlock());
            spawnedBlocks.add(768);
            blocksInitialized = true;
        }
    
        //=====================voorbeelden van blokken================================================================//
        // Blokken spawnen wanneer de player langs blockSpawnX loopt
        // Ze verschijnen blockOffsetX pixels vóór de player
        spawnBlockWhenPlayerReachesX(769, new StoneBlock(), getHeight() - 360, 312); //blok 2
        spawnBlockWhenPlayerReachesX(871, new StoneBlock(), getHeight() - 440, 312); //blok 3
        checkAndRegisterBlockVisit(974); // registreer dat blok 871 is bereikt
        spawnBlockWhenPlayerReachesX(974, new StoneBlock(), getHeight() - 440, blockOffsetX); //blok 4
        spawnCharacterWhenPlayerReachesX(974, new Beagle(), getHeight() - 540, blockOffsetX + 20); //Beagle op blok 4
        spawnBlockWhenPlayerReachesX(975, new StoneBlock(), getHeight() - 580, (blockOffsetX * -1 )); //blok 5
        spawnBlockWhenPlayerReachesX(878, new StoneBlock(), getHeight() - 580, (blockOffsetX * -1 )); //blok 6    
        removeBlockWhenVisited(974, 768); //verwijder blok 1 als je blok 3 bereikt hebt
        removeBlockWhenVisited(974, 769); //verwijder blok 2 als je blok 4 bereikt hebt
    }


//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//

    private void addBlockAtPosition(int x, int y, Block block) {
        addObject(block, x, y);
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//
    
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

    private void spawnCharacterWhenPlayerReachesX(int spawnX, Actor character, int y, int offsetX) {
        java.util.List<Hoofdpersoon> players = getObjects(Hoofdpersoon.class);
        if (players == null || players.isEmpty()) return;
        
        Hoofdpersoon player = players.get(0);
        
        // Check of karakter nog niet is gespawnd en player x-waarde heeft bereikt
        if (!spawnedBlocks.contains(spawnX + 1000) && player.getX() >= spawnX) {
            addObject(character, spawnX + offsetX, y);
            spawnedBlocks.add(spawnX + 1000);
        }
    }

//--------------------------------------------------------------------------------------------------------------------//
// CONDITIONAL SPAWNING EN REMOVAL SYSTEM - Pas deze methodes aan voor je level
//--------------------------------------------------------------------------------------------------------------------//

    /**
     * VOORBEELD: Spawnt blok X ALLEEN als je blok Y hebt bereikt
     * Gebruik: spawnBlockWithDependency(974, 769, new StoneBlock(), yPos, offsetX);
     * Dit zegt: "Spawn blok 974, maar ALLEEN als je blok 769 hebt bereikt"
     */
    private void spawnBlockWithDependency(int blockId, int requiredVisitedBlockId, Block block, int y, int offsetX) {
        java.util.List<Hoofdpersoon> players = getObjects(Hoofdpersoon.class);
        if (players == null || players.isEmpty()) return;
        
        Hoofdpersoon player = players.get(0);
        
        // Spawn ALLEEN als: 1) je het vorige blok hebt bereikt, EN 2) nog niet gespawnd
        if (!spawnedBlocks.contains(blockId) && visitedBlocks.contains(requiredVisitedBlockId) && player.getX() >= blockId) {
            addBlockAtPosition(blockId + offsetX, y, block);
            spawnedBlocks.add(blockId);
        }
    }

    /**
     * VOORBEELD: Verwijdert blokken als je het VOLGENDE blok bereikt
     * Gebruik: removeBlockWhenVisited(769, 768);
     * Dit zegt: "Verwijder blok 768, als je blok 769 hebt bereikt"
     */
    private void removeBlockWhenVisited(int visitedBlockId, int blockIdToRemove) {
        if (visitedBlocks.contains(visitedBlockId)) {
            Block blockToRemove = null;
            for (Block b : getObjects(Block.class)) {
                if (Math.abs(b.getX() - blockIdToRemove) < 50) { // Blok in buurt van blockIdToRemove
                    blockToRemove = b;
                    break;
                }
            }
            if (blockToRemove != null) {
                removeObject(blockToRemove);
            }
        }
    }

    /**
     * VOORBEELD: Markeert dat je een blok hebt bereikt (roep dit aan in renderblocks)
     * Gebruik: checkAndRegisterBlockVisit(769);
     * Dit checkt of je dicht genoeg bij blok 769 bent, en markeert het als bereikt
     */
    private void checkAndRegisterBlockVisit(int blockId) {
        java.util.List<Hoofdpersoon> players = getObjects(Hoofdpersoon.class);
        if (players == null || players.isEmpty()) return;
        
        Hoofdpersoon player = players.get(0);
        // Als player dicht genoeg bij dit blok is, markeer het als bereikt
        if (Math.abs(player.getX() - blockId) < 10 && !visitedBlocks.contains(blockId)) {
            visitedBlocks.add(blockId);
        }
    }

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------------------------------//
}