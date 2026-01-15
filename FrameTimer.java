public class FrameTimer {
    private int delay;
    private int counter = 0;

    public FrameTimer(int delay) {
        this.delay = delay;
    }

    public boolean tick() {
        counter++;
        if (counter >= delay) {
            counter = 0;
            return true;
        }
        return false;
    }
}
