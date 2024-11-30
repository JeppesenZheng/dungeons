package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;

public class Bow extends BuildableItems {

    public Bow(int durability) {
        super(durability);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }
}
