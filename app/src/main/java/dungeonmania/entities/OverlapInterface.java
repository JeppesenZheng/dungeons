package dungeonmania.entities;

import dungeonmania.map.GameMap;

public interface OverlapInterface {
    public abstract void onOverlap(GameMap map, Entity entity);
}
