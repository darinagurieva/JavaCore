package HW1;

public class Climb extends Obstacle {

    private int height;

    public Climb(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Participant participant) {
        participant.climb(height);
    }
}