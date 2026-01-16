public class IceBlock extends Block {

    public IceBlock() {
        super("ice.png");
    }

    @Override
    public double getFriction() {
        return 0.2; // very slippery
    }
}
