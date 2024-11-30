package dungeonmania.entities.logic;

import java.util.List;

import dungeonmania.util.Position;

public abstract class LogicalRules extends Logic {
    private RuleStrategy ruleStrategy;

    public LogicalRules(Position position, String rule) {
        super(position);
        this.ruleStrategy = RuleFactory.getRuleStrategy(rule);
    }

    public void setActivated(boolean activate, int tick) {
        if (checkRule(tick)) {
            super.setActivate(true);
        } else {
            super.setActivate(false);
        }
    }

    public boolean checkRule(int tick) {
        List<Logic> adjLogics = getAdjLogicEntities();
        return ruleStrategy.evaluate(adjLogics, tick);
    }

}
