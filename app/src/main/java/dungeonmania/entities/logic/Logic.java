package dungeonmania.entities.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Switch;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Logic extends Entity implements InventoryItem {
    private boolean activate = false;
    private List<Logic> adjLogicEntities = new ArrayList<>();

    public Logic(Position position) {
        super(position.asLayer(Entity.DOOR_LAYER));
    }

    public boolean isActivated() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public List<Logic> getAdjLogicEntities() {
        return adjLogicEntities;
    }

    public void bfsActivate(GameMap map, boolean activated, int currentTick) {
        Queue<Conductor> conductorQueue = new LinkedList<>();
        Queue<LogicalRules> logicalRulesQueue = new LinkedList<>();
        Set<Logic> processedLogics = new HashSet<>();

        if (this instanceof Conductor) {
            conductorQueue.add((Conductor) this);
            processedLogics.add(this);
        }

        // get all the conductors first
        while (!conductorQueue.isEmpty()) {
            Conductor currentConductor = conductorQueue.poll();
            currentConductor.setActivated(map, activated, currentTick);

            for (Logic logic : currentConductor.getAdjLogicEntities()) {
                if (!processedLogics.contains(logic)) {
                    // if find any swtich is on then kill the bfs.
                    if (logic.isActivated() && logic instanceof Switch && !activated) {
                        return;
                    }
                    processedLogics.add(logic);
                    if (logic instanceof Conductor && !(logic instanceof Switch)) {
                        conductorQueue.add((Conductor) logic);
                    } else if (logic instanceof LogicalRules) {
                        logicalRulesQueue.add((LogicalRules) logic);
                    }
                }
            }
        }

        // then deal with the logical entities
        while (!logicalRulesQueue.isEmpty()) {
            LogicalRules currentRule = logicalRulesQueue.poll();
            currentRule.setActivated(activated, currentTick);
        }
    }

    public void subscribe(Logic logic) {
        adjLogicEntities.add(logic);
    }

}
