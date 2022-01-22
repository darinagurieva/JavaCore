package HW1;

public class Run extends Obstacle {

    private int length;

    public Run(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Participant animal) {
        animal.run(length);
    }
}