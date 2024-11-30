package dungeonmania.entities.enemies;

// import java.util.List;
// import java.util.Random;
// import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
// import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;
    // private Random randGen = new Random();
    private MoveStrategy moveStrategy;

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        if (map.getPlayerPotion() instanceof InvincibilityPotion) {
            this.setMoveStrategy(new FleeMove(this));
        } else {
            this.setMoveStrategy(new ZombieToastMove(this));
        }
        this.moveStrategy.move(game);
    }

}
