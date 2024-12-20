package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
// import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class FleeMove implements MoveStrategy {
    private Entity entity;

    public FleeMove(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        Position nextPos;
        Position plrDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(), entity.getPosition());

        Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(entity.getPosition(), Direction.RIGHT)
                : Position.translateBy(entity.getPosition(), Direction.LEFT);
        Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(entity.getPosition(), Direction.UP)
                : Position.translateBy(entity.getPosition(), Direction.DOWN);
        Position offset = entity.getPosition();
        if (plrDiff.getY() == 0 && map.canMoveTo(entity, moveX))
            offset = moveX;
        else if (plrDiff.getX() == 0 && map.canMoveTo(entity, moveY))
            offset = moveY;
        else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
            if (map.canMoveTo(entity, moveX))
                offset = moveX;
            else if (map.canMoveTo(entity, moveY))
                offset = moveY;
            else
                offset = entity.getPosition();
        } else {
            if (map.canMoveTo(entity, moveY))
                offset = moveY;
            else if (map.canMoveTo(entity, moveX))
                offset = moveX;
            else
                offset = entity.getPosition();
        }
        nextPos = offset;
        map.moveTo(entity, nextPos);
    }
}
