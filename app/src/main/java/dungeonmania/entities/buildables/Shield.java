package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;

public class Shield extends BuildableItems {
    private double defence;

    public Shield(int durability, double defence) {
        super(durability);
        this.defence = defence;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, defence, 1, 1));
    }
}
