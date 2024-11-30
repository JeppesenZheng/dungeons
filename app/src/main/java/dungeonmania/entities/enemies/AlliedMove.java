package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class AlliedMove implements MoveStrategy {
    private Mercenary mercenary;

    public AlliedMove(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    @Override
    public void move(Game game) {
        Position nextPos;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        if (mercenary.isAdjacentToPlayer()) {
            nextPos = player.getPreviousDistinctPosition();
        } else {
            nextPos = map.dijkstraPathFind(mercenary.getPosition(), player.getPosition(), mercenary);
            // mercenary and player probably go to the same position, if it is, get player previous position
            if (nextPos.equals(player.getPosition())) {
                nextPos = player.getPreviousDistinctPosition();
            } else if (Position.isAdjacent(player.getPosition(), nextPos)) {
                mercenary.setAdjacentToPlayer(true);
            }
        }
        map.moveTo(mercenary, nextPos);
    }
}
