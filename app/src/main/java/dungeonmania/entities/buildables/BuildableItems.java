package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;

public abstract class BuildableItems extends Buildable implements BattleItem {
    private int durability;

    public BuildableItems(int durability) {
        super(null);
        this.durability = durability;
    }

    public void use(Game game) {
        durability--;
        if (durability <= 0) {
            game.removeItem(this);
        }
    }

    public int getDurability() {
        return durability;
    }

    public abstract BattleStatistics applyBuff(BattleStatistics origin);
}
