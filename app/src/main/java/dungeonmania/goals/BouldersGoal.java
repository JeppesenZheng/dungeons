package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.Switch;

public class BouldersGoal extends Goal {

    @Override
    public boolean achieved(Game game) {
        return game.getEntitiesWithClass(Switch.class).stream().allMatch(s -> s.isActivated());
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game)) return "";
        return ":boulders";
    }
}
