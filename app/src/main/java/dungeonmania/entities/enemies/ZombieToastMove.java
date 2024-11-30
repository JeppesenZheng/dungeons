package dungeonmania.entities.enemies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ZombieToastMove implements MoveStrategy {
    private ZombieToast zombieToast;
    private Random randGen = new Random();

    public ZombieToastMove(ZombieToast zombieToast) {
        this.zombieToast = zombieToast;
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        Position nextPos;
        List<Position> pos = zombieToast.getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(zombieToast, p)).collect(Collectors.toList());
        if (pos.size() == 0) {
            nextPos = zombieToast.getPosition();
        } else {
            nextPos = pos.get(randGen.nextInt(pos.size()));
        }
        game.getMap().moveTo(zombieToast, nextPos);
    }
}
