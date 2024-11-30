package dungeonmania.entities.enemies;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public class SpiderMove implements MoveStrategy {
    private Spider spider;

    public SpiderMove(Spider spider) {
        this.spider = spider;
    }
    @Override
    public void move(Game game) {
        Position nextPos = spider.getMovementTrajectory().get(spider.getNextPositionElement());
        List<Entity> entities = game.getMap().getEntities(nextPos);
        if (entities != null && entities.size() > 0 && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            spider.setForward(!spider.isForward());
            spider.updateNextPosition();
            spider.updateNextPosition();
        }
        nextPos = spider.getMovementTrajectory().get(spider.getNextPositionElement());
        entities = game.getMap().getEntities(nextPos);
        if (entities == null || entities.size() == 0
                || entities.stream().allMatch(e -> e.canMoveOnto(game.getMap(), spider))) {
            game.getMap().moveTo(spider, nextPos);
            spider.updateNextPosition();
        }
    }

}
