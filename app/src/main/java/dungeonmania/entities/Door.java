package dungeonmania.entities;

import dungeonmania.map.GameMap;

import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.util.Position;

public class Door extends Entity implements OverlapInterface {
    private boolean open = false;
    private int number;

    public Door(Position position, int number) {
        super(position.asLayer(Entity.DOOR_LAYER));
        this.number = number;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        if (open || entity instanceof Spider) {
            return true;
        }
        return (entity instanceof Player && ((hasKey((Player) entity)) || hasSunStone((Player) entity)));
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (!(entity instanceof Player))
            return;

        Player player = (Player) entity;
        Key key = player.getInventoryItem(Key.class);
        System.out.println("Check keys");
        if (hasKey(player)) {
            player.remove(key);
            open();
        }

        if (hasSunStone(player)) {
            System.out.println("check insdie");
            open();
        }
    }

    // have violation of Lod, same as hasSunStone
    private boolean hasKey(Player player) {
        Key key = player.getInventoryItem(Key.class);
        return (key != null && key.getnumber() == number);
    }

    private boolean hasSunStone(Player player) {
        SunStone sunStone = player.getInventoryItem(SunStone.class);
        System.out.println(sunStone);

        return (sunStone != null);
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

}
