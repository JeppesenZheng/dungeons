package dungeonmania.entities.logic;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.collectables.Bomb;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Conductor extends Logic {
    private int currentTick = -1;
    private List<Bomb> bombs = new ArrayList<>();

    public Conductor(Position position) {
        super(position);
    }

    public int getCurrentTick() {
        return currentTick;
    }

    public void setCurrentTick(int currentTick) {
        this.currentTick = currentTick;
    }

    public void subscribe(Bomb b) {
        System.out.println("adding bomb");
        bombs.add(b);
    }

    public void subscribe(Bomb bomb, GameMap map) {
        bombs.add(bomb);
        if (isActivated()) {
            bombs.stream().forEach(b -> b.notify(map));
        }
    }

    public void unsubscribe(Bomb b) {
        bombs.remove(b);
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void setActivated(GameMap map, boolean activate, int currentTick) {
        super.setActivate(activate);
        if (activate) {
            for (Bomb bomb : bombs) {
                if (bomb.checkRule(currentTick)) {
                    bomb.notify(map);
                }
            }
        }
        setCurrentTick(currentTick);
    }

}
