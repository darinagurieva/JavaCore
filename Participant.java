package HW1;

public interface Participant {

    String getName();

    boolean isOnDistance();

    void run(int distance);

    void climb(int height);

    void swim(int distance);
}