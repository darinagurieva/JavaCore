package HW1;

public class Dog extends Animal {

    public Dog(String name, int maxRun, int maxClimb, int maxSwim) {
        super(name, maxRun, maxClimb, maxSwim);
    }

    @Override
    public void voice() {
        System.out.println("Bark!");
    }

    public void swim() {
        System.out.println(getName() + " is swimmig!");
    }
}