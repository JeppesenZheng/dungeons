package dungeonmania.goals;

import dungeonmania.Game;

public abstract class Goal {
    // private String type;
    // private int target;
    // private Goal goal1;
    // private Goal goal2;

    // public Goal(String type) {
    //     this.type = type;
    // }

    // public Goal(int target) {
    //     this.target = target;
    // }

    // public Goal(String type, int target) {
    //     this.type = type;
    //     this.target = target;
    // }

    // public Goal(Goal goal1, Goal goal2) {
    //     this.goal1 = goal1;
    //     this.goal2 = goal2;
    // }

    // public Goal(String type, Goal goal1, Goal goal2) {
    //     this.type = type;
    //     this.goal1 = goal1;
    //     this.goal2 = goal2;
    // }

    /**
     * @return true if the goal has been achieved, false otherwise
     */
    public abstract boolean achieved(Game game);

    public abstract String toString(Game game);
}
