package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class HostileMove implements MoveStrategy {
    private Mercenary mercenary;

    public HostileMove(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        Position nextPos = map.dijkstraPathFind((mercenary.getPosition()), player.getPosition(), mercenary);
        map.moveTo(mercenary, nextPos);
    }
}
