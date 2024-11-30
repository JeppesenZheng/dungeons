package dungeonmania.entities.enemies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class RandomMove implements MoveStrategy {
    private Mercenary mercenary;

    public RandomMove(Mercenary mercenary) {
        this.mercenary = mercenary;
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        Random randGen = new Random();
        List<Position> pos = mercenary.getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(mercenary, p)).collect(Collectors.toList());
        Position nextPos = pos.isEmpty() ? mercenary.getPosition() : pos.get(randGen.nextInt(pos.size()));
        map.moveTo(mercenary, nextPos);
    }

}
