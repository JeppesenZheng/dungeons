package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dungeonmania.util.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class SceptreTest {
    @Test
    @Tag("6-1")
    @DisplayName("Test building Sceptre with no much material")
    public void buildSceptreInvalidActionException() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_sceptre_ErrorTestsMaterials", "c_SceptreTest");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        // get sun stone
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // there isa zombie toast in map, so it can't build midnight armour
        assertThrows(InvalidActionException.class, () -> dmc.build("sceptre"));
    }

    @Test
    @Tag("6-2")
    @DisplayName("Test building Sceptre successfully1")
    public void buildSceptreSuccesssfully1() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_sceptre_SuccessBuild", "c_SceptreTest");

        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "key").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
    }

    @Test
    @Tag("6-3")
    @DisplayName("Test building Sceptre successfully2")
    public void buildSceptreSuccesssfully2() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_sceptre_SuccessBuild", "c_SceptreTest");
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
    }

    @Test
    @Tag("6-4")
    @DisplayName("Test building Sceptre with sunstone replace key or treasure and be retained after crafting")
    public void buildSceptreSuccesssfully3() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_sceptre_SuccessBuild", "c_SceptreTest");
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "wood").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
    }

    @Test
    @Tag("6-5")
    @DisplayName("Test building Sceptre consume treasure rather than sunstone")
    public void buildSceptreSuccesssfully4() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_sceptre_SuccessBuild", "c_SceptreTest");
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
    }

    @Test
    @Tag("6-6")
    @DisplayName("Test building Sceptre consume key rather than sunstone")
    public void buildSceptreSuccesssfully5() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_sceptre_SuccessBuild", "c_SceptreTest");
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(1, TestUtils.getInventory(res, "key").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
    }

    @Test
    @Tag("6-7")
    @DisplayName("Use sceptre to mind contorl mecenary")
    public void mindControlTest() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_Sceptre_mindControlTest", "c_Sceptre_mindControlTest");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        // get wood
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(8, 7), getMercPos(res));

        // get sun stones
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(8, 6), getMercPos(res));
        res = dmc.tick(Direction.LEFT);
        assertEquals(new Position(8, 5), getMercPos(res));
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(8, 4), getMercPos(res));
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(7, 4), getMercPos(res));

        // build sceptre
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));

        assertEquals(new Position(6, 4), getMercPos(res));
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(5, 4), getMercPos(res));
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(4, 4), getMercPos(res));

        res = assertDoesNotThrow(() -> dmc.interact(mercId));

        assertEquals(new Position(2, 5), getPlayerPos(res));
        assertEquals(new Position(3, 4), getMercPos(res));
        res = dmc.tick(Direction.RIGHT);

        // check player and merc position
        // mrec should go to player's previous position, cause merc is allied now
        assertEquals(new Position(3, 5), getPlayerPos(res));
        assertEquals(new Position(2, 5), getMercPos(res));
    }

    @Test
    @Tag("6-8")
    @DisplayName("Test sceptre can only mind control one mercenary one time")
    public void miodControlTest1() {
        // it's similart with 6-7, just added another mercenary at (8,9)
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_Sceptre_mindControlTest1", "c_Sceptre_mindControlTest");
        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        // get wood
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(8, 7), getMercPos(res));

        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        // build sceptre
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        res = assertDoesNotThrow(() -> dmc.interact(mercId));
        res = dmc.tick(Direction.RIGHT);
        // not mindcontrolled mercenary should be below the player
        assertTrue(TestUtils.entityAtPosition(res, "mercenary", new Position(4, 5)));

    }

    private Position getPlayerPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "player").get(0).getPosition();
    }

    private Position getMercPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "mercenary").get(0).getPosition();
    }
}
