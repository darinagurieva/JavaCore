package HW1;

public class Cat extends Animal {

    public Cat(String name, int maxRun,
            int maxClimb, int maxSwim) {
        super(name, maxRun, maxClimb, maxSwim);
    }

    @Override
    public void voice() {
        System.out.println("Meow!");
    }
}