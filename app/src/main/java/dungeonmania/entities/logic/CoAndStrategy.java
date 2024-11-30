package dungeonmania.entities.logic;

import java.util.List;

public class CoAndStrategy implements RuleStrategy {

    @Override
    public boolean evaluate(List<Logic> adjacentLogics, int tick) {
        return adjacentLogics.stream()
                             .filter(logic -> logic.isActivated()
                                    && logic instanceof Conductor && ((Conductor) logic).getCurrentTick() == tick)
                             .count() >= 2;
    }
}
