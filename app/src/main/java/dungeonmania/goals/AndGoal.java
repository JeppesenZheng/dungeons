package dungeonmania.goals;

import dungeonmania.Game;

public class AndGoal extends Goal {
    private Goal goal1;
    private Goal goal2;

    public AndGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public boolean achieved(Game game) {
        System.out.println("goal1 " + goal1.toString(game));
        System.out.println("goal2 " + goal2.toString(game));
        return goal1.achieved(game) && goal2.achieved(game);
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game)) return "";
        return "(" + goal1.toString(game) + " AND " + goal2.toString(game) + ")";
    }
}
