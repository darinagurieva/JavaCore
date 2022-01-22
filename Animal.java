package HW1;

public abstract class Animal implements Participant {

    private String name;
    private boolean onDistance;
    private int maxRun;
    private int maxClimb;
    private int maxSwim;

    public Animal(String name, int maxRun, int maxClimb, int maxSwim) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxClimb = maxClimb;
        this.maxSwim = maxSwim;
        this.onDistance = true;
    }

    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public abstract void voice();

    @Override
    public void run(int distance) {
        if (distance <= maxRun) {
            System.out.println(name + " run succesfully!");
        } else {
            System.out.println(name + " failed running!");
            onDistance = false;
        }
    }

    @Override
    public void climb(int height) {
        if (height <= maxClimb) {
            System.out.println(name + " jumped succesfully!");
        } else {
            System.out.println(name + " failed jumping!");
            onDistance = false;
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= maxSwim) {
            System.out.println(name + " swam succesfully!");
        } else {
            System.out.println(name + " failed swimming!");
            onDistance = false;
        }
    }

    @Override
    public String toString() {
        return name + " : " + (isOnDistance() ? "is on its way!" : "went out!");
    }
}