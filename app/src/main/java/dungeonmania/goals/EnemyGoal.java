package dungeonmania.goals;

import dungeonmania.Game;

public class EnemyGoal extends Goal {
    private int target;

    public EnemyGoal(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        return game.checkHasSpawner() && game.getKillEnemyCount() >= target;
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game)) return "";
        return ":enemies";
    }
}
