package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class LogicTest {
    @Test
    @Tag("7-1")
    @DisplayName("simple turn on light bulb")
    public void simpleTurnOnLightBulb() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicSimpleLightBulb", "c_boulderTest_pushBoulder");

        // push the boulder onto switch
        res = dmc.tick(Direction.RIGHT);

        // check how many lights is on
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        // to move away the boulder
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // no light bulb is on
        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-2")
    @DisplayName("simple open the switch door")
    public void simpleOpenSwitchDoor() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicSimpleSwitchDoor", "c_boulderTest_pushBoulder");

        // push the boulder onto switch
        res = dmc.tick(Direction.RIGHT);

        // check how many doors is open
        assertEquals(1, TestUtils.countType(res, "switch_door_open"));

        // to move away the boulder
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // no door is open
        assertEquals(0, TestUtils.countType(res, "switch_door_on"));
    }

    @Test
    @Tag("7-3")
    @DisplayName("simple turn on light bulb via Wire")
    public void simpleTurnOnLightBulbViaWire() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicSimpleLightBulbViaWire", "c_boulderTest_pushBoulder");

        // push the boulder onto switch
        res = dmc.tick(Direction.RIGHT);

        // check how many lights in on
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        // to move away the boulder
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // no light bulb is on
        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-4")
    @DisplayName("turn on light with or rule")
    public void turnOnLightBulbOrRule() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicOrLightBulb", "c_boulderTest_pushBoulder");

        // activate the first switch
        res = dmc.tick(Direction.RIGHT);

        // light should be on
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        // activate the second swtich
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);

        // light is still on
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-5")
    @DisplayName("turn on light with and rule")
    public void turnOnLightBulbAndRule() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicAndLightBulb", "c_boulderTest_pushBoulder");

        // activate the first switch
        res = dmc.tick(Direction.RIGHT);

        // light should be off
        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));

        // activate the second swtich
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);

        // light is still on
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-6")
    @DisplayName("turn on light with Xor rule")
    public void turnOnLightBulbXorRule() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicXorLightBulb", "c_boulderTest_pushBoulder");

        // activate swtich(2,1)
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);

        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        // move back to activate swtich (2,0)
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);

        // now light should be off
        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-7")
    @DisplayName("turn on light with Co_and rule")
    public void turnOnLightBulbCoAndRule() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicCoAndLightBulb", "c_boulderTest_pushBoulder");

        // activate the switch and light should be on
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-8")
    @DisplayName("turn on light with Co_and rule")
    public void turnOnLightBulbCoAndRule2() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicCoAndLightBulb", "c_boulderTest_pushBoulder");

        // activate the switch(5,-1)
        for (int i = 0; i < 4; i++) {
            res = dmc.tick(Direction.UP);
        }
        for (int i = 0; i < 4; i++) {
            res = dmc.tick(Direction.RIGHT);
        }
        res = dmc.tick(Direction.DOWN);

        // light shoule be on
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        // try to turn off the light
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.LEFT);
        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-9")
    @DisplayName("activate bomb with co_and rule")
    public void activateBombCoAndRule() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicCoAndBomb", "c_logic_CoAndBomb");

        // activate the switch and bomb should be activated
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countType(res, "bomb"));
        // only has player in the map
        assertEquals(1, TestUtils.getNumOfAllEntities(res));
    }

    @Test
    @Tag("7-10")
    @DisplayName("multi light bulbs with differnt logic rules")
    public void multiLightBulbsDifLogicRules() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicMutiLightsDiffLogicRule", "c_logic_CoAndBomb");

        // activate the swtich and all the lights will light up
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-11")
    @DisplayName("can't activatet the light with co_and logic rule")
    public void cannotTurnOnLightWithCoAnd() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sampleCoAndLogicRule", "c_logic_CoAndBomb");

        // move the upper boulder first
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);

        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));

        // move to the lower boulder
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));

        // move away upper and lower boulder
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.UP);

        // move to the middle bolder
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-12")
    @DisplayName("checking if two switches light up the same light, and inactivate one of those two switches")
    public void edgecase1() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicCoAndLightBulb", "c_boulderTest_pushBoulder");

        // activate first switch
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        // activate the second switch
        for (int i = 0; i < 4; i++) {
            res = dmc.tick(Direction.UP);
        }
        for (int i = 0; i < 3; i++) {
            res = dmc.tick(Direction.RIGHT);
        }
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        // move it away
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.LEFT);
        // light is still on
        assertEquals(1, TestUtils.countType(res, "light_bulb_on"));

        for (int i = 0; i < 2; i++) {
            res = dmc.tick(Direction.LEFT);
        }

        for (int i = 0; i < 2; i++) {
            res = dmc.tick(Direction.DOWN);
        }
        assertEquals(0, TestUtils.countType(res, "light_bulb_on"));
    }

    @Test
    @Tag("7-13")
    @DisplayName("check player can go through the switch_door if door is activated")
    public void goThroughSwitchDoor() {
        // SWI: switch; BOU: boulder; PLA: player; SWD: switch_door; EXI: exit
        //      BOU
        //      SWI
        //  PLA SWD EXI
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicSwitchDoorEnter", "c_boulderTest_pushBoulder");

        // activate the switch
        for (int i = 0; i < 3; i++) {
            res = dmc.tick(Direction.UP);
        }

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.countType(res, "switch_door_open"));

        // go through the door
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // go to exit
        res = dmc.tick(Direction.RIGHT);

        // get the exit goal
        assertEquals("", TestUtils.getGoals(res));
    }
}
