package HW1;

public class Main {
    public static void main(String[] args) {

        Course c = new Course(new Obstacle[] { new Run(10), new Climb(3), new Swim(5) });
        Team team = new Team("Champions", new Cat("Simon", 25, 8, 3), new Dog("Belka", 50, 2, 20),
                new Cat("Cream Puff", 10, 14, 6), new Dog("Strelka", 45, 4, 25));
        team.getTeamInfo();
        c.doIt(team);
        team.showResults();
    }
}