package dungeonmania.entities.logic;

import java.util.List;

public class XorStrategy implements RuleStrategy {

    @Override
    public boolean evaluate(List<Logic> adjacentLogics, int tick) {
        return adjacentLogics.stream()
                             .filter(Logic::isActivated)
                             .count() == 1;
    }
}
