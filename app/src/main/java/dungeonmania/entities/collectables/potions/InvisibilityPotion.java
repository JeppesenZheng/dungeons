package dungeonmania.entities.collectables.potions;

import dungeonmania.entities.playerState.PlayerState;
import dungeonmania.util.Position;

public class InvisibilityPotion extends Potion {
    public static final int DEFAULT_DURATION = 8;

    public InvisibilityPotion(Position position, int duration) {
        super(position, duration);
    }

    @Override
    public void applyEffect(PlayerState playerState) {
        playerState.transitionInvisible();
    }


}
