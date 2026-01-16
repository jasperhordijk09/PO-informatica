public class SlimeBlock extends Block {

    public SlimeBlock() {
        super("slime.png");
    }

    @Override
    public double getFriction() {
        return 2.0; // very sticky
    }
}
