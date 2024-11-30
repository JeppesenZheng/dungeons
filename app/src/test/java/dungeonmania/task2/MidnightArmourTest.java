package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class MidnightArmourTest {
    @Test
    @Tag("3-1")
    @DisplayName("Test InvalidActionException is raised when attempting to build midnight_armour"
                + " - when there is a zombieToast")
    public void buildMidnightArmourInvalidActionException() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_midnightArmour_ErrorTests", "c_MidnightArmourTest");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        // get sun stone
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        res = dmc.tick(Direction.DOWN);
        // get sword
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // there is a zombie toast in map, so it can't build midnight armour
        assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));
    }

    @Test
    @Tag("3-2")
    @DisplayName("Test InvalidActionException is raised when attempting to build midnight_armour"
                + " - don't have enough materials")
    public void buildMidnightArmourInvalidActionException2() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_midnight_ErrorTestsMaterials", "c_MidnightArmourTest");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        // get sun stone
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // can't build cause doesn't have enough materials
        assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));
    }

    @Test
    @Tag("3-3")
    @DisplayName("successfully build the midnight armour")
    public void successfullyBuildMidnightArmour() {
        DungeonManiaController dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_midnightArmour_build", "c_MidnightArmourTest");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        // get sun stone
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        res = dmc.tick(Direction.DOWN);
        // get sword
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // build midnigt_armour
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

        // materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
    }
}
