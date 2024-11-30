package dungeonmania.entities;

// import java.util.ArrayList;
// import java.util.List;

// import dungeonmania.entities.collectables.Bomb;
import dungeonmania.entities.logic.Conductor;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Conductor implements OverlapInterface, OnmoveInterface {
    private boolean activated;
    // private List<Bomb> bombs = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    // public void subscribe(Bomb b) {
    //     bombs.add(b);
    // }

    // public void subscribe(Bomb bomb, GameMap map) {
    //     bombs.add(bomb);
    //     if (activated) {
    //         bombs.stream().forEach(b -> b.notify(map));
    //     }
    // }

    // public void unsubscribe(Bomb b) {
    //     bombs.remove(b);
    // }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = true;
            if (!adjLogicEntitiesIsEmpty()) {
                bfsActivate(map, true, map.getGameTick());
            }
            getBombs().stream().forEach(b -> b.notify(map));
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = false;
            if (!adjLogicEntitiesIsEmpty()) {
                bfsActivate(map, false, map.getGameTick());
            }
        }
    }

    public boolean isActivated() {
        return activated;
    }

    private boolean adjLogicEntitiesIsEmpty() {
        return getAdjLogicEntities().isEmpty();
    }

}
