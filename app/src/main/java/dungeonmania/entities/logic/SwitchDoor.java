package dungeonmania.entities.logic;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class SwitchDoor extends LogicalRules {

    public SwitchDoor(Position position, String rule) {
        super(position, rule);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        if (isActivated() || entity instanceof Spider) {
            return true;
        }
        return !(entity instanceof Player);
    }

}
