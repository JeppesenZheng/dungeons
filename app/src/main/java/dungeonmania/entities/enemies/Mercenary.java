package dungeonmania.entities.enemies;

// import java.util.List;
// import java.util.Random;
// import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.map.GameMap;
// import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Mercenary extends Enemy implements Interactable {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;

    private int bribeAmount = Mercenary.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Mercenary.DEFAULT_BRIBE_RADIUS;

    private double allyAttack;
    private double allyDefence;
    private boolean allied = false;
    private boolean isAdjacentToPlayer = false;
    private boolean isMindContorlled = false;
    private int mindControlDuration = 0;
    private MoveStrategy moveStrategy;

    public Mercenary(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence) {
        super(position, health, attack);
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        this.allyAttack = allyAttack;
        this.allyDefence = allyDefence;
    }

    public boolean isAllied() {
        return allied;
    }

    public boolean isAdjacentToPlayer() {
        return isAdjacentToPlayer;
    }

    public void setAdjacentToPlayer(boolean isAdjacentToPlayer) {
        this.isAdjacentToPlayer = isAdjacentToPlayer;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        System.out.println("\n\n inside onOverlap");
        System.out.println(allied);
        if (allied)
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current merc can be bribed
     * @param player
     * @return
     */
    private boolean canBeBribed(Player player) {
        Position playerPos = player.getPosition();
        Position mercenaryPos = getPosition();
        int dx = Math.abs(playerPos.getX() - mercenaryPos.getX());
        int dy = Math.abs(playerPos.getY() - mercenaryPos.getY());
        int distance = dx + dy;
        return distance <= bribeRadius && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    /**
     * bribe the merc
     */
    private void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }

    }

    /**
     * time tick
    */
    public void mindControlTick() {
        if (isMindContorlled) {
            mindControlDuration--;
            if (mindControlDuration <= 0) {
                isMindContorlled = false;
                allied = false;
            }
        }
    }

    public void setMindControl(int time) {
        this.mindControlDuration = time;
        this.isMindContorlled = true;
        this.allied = true;
    }

    @Override
    public void interact(Player player, Game game) {
        if (player.hasSceptre()) {
            player.useSceptre(game, getId());
            if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), getPosition()))
                isAdjacentToPlayer = true;
            return;
        }
        allied = true;
        bribe(player);
        if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), getPosition()))
            isAdjacentToPlayer = true;
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        if ((isAllied() || this.isMindContorlled) && !hasAnyPotion(map)) {
            System.out.println("use allied movement");
            this.mindControlTick();
            this.setMoveStrategy(new AlliedMove(this));
        } else if (map.getPlayerPotion() instanceof InvisibilityPotion) {
            this.setMoveStrategy(new RandomMove(this));
        } else if (map.getPlayerPotion() instanceof InvincibilityPotion) {
            this.setMoveStrategy(new FleeMove(this));
        } else {
            this.setMoveStrategy(new HostileMove(this));
        }
        this.moveStrategy.move(game);
    }

    @Override
    public boolean isInteractable(Player player) {
        return (!allied && canBeBribed(player)) || player.hasSceptre();
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        if (!allied)
            return super.getBattleStatistics();
        return new BattleStatistics(0, allyAttack, allyDefence, 1, 1);
    }

    private boolean hasAnyPotion(GameMap map) {
        return (map.getPlayerPotion() instanceof InvisibilityPotion)
                || (map.getPlayerPotion() instanceof InvincibilityPotion);
    }
}
