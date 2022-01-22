package HW1;

public class Team {
    private String name;
    private Participant participants[];

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, Participant... participantsDef) {
        this.name = name;
        this.participants = participantsDef;
    }

    public void getTeamInfo() {
        System.out.println("Team: " + this.name);
        for (Participant participant : participants) {
            if (participant instanceof Dog) {
                System.out.println("Doggo " + participant.getName());
            }
            if (participant instanceof Cat) {
                System.out.println("Catto " + participant.getName());
            }
        }
    }

    public void showResults() {
        for (Participant participant : participants) {
            if (!participant.isOnDistance()) {
                break;
            }
        }
    }

    public void doIt(Obstacle obstacle) {
        for (Participant participant : participants) {
            obstacle.doIt(participant);
        }
    }
}