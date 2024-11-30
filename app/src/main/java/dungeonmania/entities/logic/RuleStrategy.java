package dungeonmania.entities.logic;

import java.util.List;

public interface RuleStrategy {
    boolean evaluate(List<Logic> adjacentLogics, int tick);
}
