package dungeonmania.entities.buildables;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Mercenary;

public class Sceptre extends Buildable {
    private int mindControlDuration;
    private List<Mercenary> mercenaries;

    public Sceptre(int mindControlDuration) {
        super(null);
        this.mindControlDuration = mindControlDuration;
    }

    public void mindControlMercenary(Game game, String entityId) {
        mercenaries = game.getEntitiesWithClass(Mercenary.class);
        System.out.println("mindcontroling");
        for (Mercenary mercenary: mercenaries) {
            if (mercenary.getId() == entityId) {
                mercenary.setMindControl(mindControlDuration);
            }
        }
    }
}
