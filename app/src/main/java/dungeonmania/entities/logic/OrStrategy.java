package dungeonmania.entities.logic;

import java.util.List;

public class OrStrategy implements RuleStrategy {

    @Override
    public boolean evaluate(List<Logic> adjacentLogics, int tick) {
        return adjacentLogics.stream()
                             .filter(Logic::isActivated)
                             .count() >= 1;
    }
}
