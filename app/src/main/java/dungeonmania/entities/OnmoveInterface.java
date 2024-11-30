package dungeonmania.entities;

import dungeonmania.map.GameMap;

public interface OnmoveInterface {
    public abstract void onMovedAway(GameMap map, Entity entity);
}
