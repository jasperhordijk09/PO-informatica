import java.util.*;

public class Animations {

    // Base folders
    public static final String LEFT_FOLDER  = "images/gyro_images/left";
    public static final String RIGHT_FOLDER = "images/gyro_images/right";

    // Walking animation order (you can reorder freely)
    public static final String[] WALK_ORDER = {
        "sprite_004.png",
        "sprite_002.png",
        "sprite_010.png",
        "sprite_003.png",
        "sprite_001.png",
        "sprite_007.png",
        "sprite_006.png",
        "sprite_009.png",
        "sprite_005.png",
        "sprite_008.png",
        "sprite_011.png",
        "sprite_013.png"
    };

    // Idle animation order (example)
    public static final String[] IDLE_ORDER = {
        "sprite_001.png"
    };

    // Utility: returns a map of all animations for a character
    public static Map<String, AnimationDefinition> getAll() {
        Map<String, AnimationDefinition> map = new HashMap<>();

        map.put("WalkingLeft",  new AnimationDefinition(LEFT_FOLDER,  WALK_ORDER));
        map.put("WalkingRight", new AnimationDefinition(RIGHT_FOLDER, WALK_ORDER));

        map.put("IdleLeft",  new AnimationDefinition(LEFT_FOLDER,  IDLE_ORDER));
        map.put("IdleRight", new AnimationDefinition(RIGHT_FOLDER, IDLE_ORDER));

        return map;
    }

    // Inner class to hold folder + file order
    public static class AnimationDefinition {
        public final String folder;
        public final String[] files;

        public AnimationDefinition(String folder, String[] files) {
            this.folder = folder;
            this.files = files;
        }
    }
}
