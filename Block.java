import greenfoot.*;

public class Block extends Actor {

    public Block(String imageName) {
        setImage(imageName);
    }

    // Default friction (normal ground)
    public double getFriction() {
        return 1.0;
    }
}
