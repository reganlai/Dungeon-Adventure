/*
 * TCSS 360 - Dungeon Adventure
 */
package test;

import Model.Room;
import Model.WallType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Unit tests for the {@link Model.Room} class.
 * This class verifies the behavior of various methods in the Room class
 * such as setting and getting wall types, room occupants, and handling room state.
 *
 * @author George Njane
 * @version 12.13.24
 */
public class RoomTest {
    private Room myRoom;

    /**
     * Initializes a default {@link Room} instance before each test.
     */
    @Before
    public void setUp() {
        myRoom = Room.defualtRoom();
    }

    /**
     * Tests setting and retrieving the room occupant.
     */
    @Test
    public void testSetRoomOccupantAndGetRoomOccupant() {
        assertEquals("", myRoom.getRoomOccupant());
        myRoom.setRoomOccupant("H");
        assertEquals("H", myRoom.getRoomOccupant());
    }

    /**
     * Tests setting and retrieving the type of the north wall.
     */
    @Test
    public void testSetNorthWall() {
        assertEquals(WallType.HORIZONTAL_WALL, myRoom.getNorthWall());
        myRoom.setNorthWall(WallType.HORIZONTAL_DOOR);
        assertEquals(WallType.HORIZONTAL_DOOR, myRoom.getNorthWall());
    }

    /**
     * Tests setting and retrieving the type of the south wall.
     */
    @Test
    public void testSetSouthWall() {
        assertEquals(WallType.HORIZONTAL_WALL, myRoom.getSouthWall());
        myRoom.setSouthWall(WallType.HORIZONTAL_DOOR);
        assertEquals(WallType.HORIZONTAL_DOOR, myRoom.getSouthWall());
    }

    /**
     * Tests setting and retrieving the type of the west wall.
     */
    @Test
    public void testSetWestWall() {
        assertEquals(WallType.VERTICAL_WALL, myRoom.getWestWall());
        myRoom.setWestWall(WallType.VERTICAL_DOOR);
        assertEquals(WallType.VERTICAL_DOOR, myRoom.getWestWall());
    }

    /**
     * Tests setting and retrieving the type of the east wall.
     */
    @Test
    public void testSetEastWall() {
        assertEquals(WallType.VERTICAL_WALL, myRoom.getEastWall());
        myRoom.setEastWall(WallType.VERTICAL_DOOR);
        assertEquals(WallType.VERTICAL_DOOR, myRoom.getEastWall());
    }

    /**
     * Tests setting a visiting string and retrieving the room string representation.
     */
    @Test
    public void testSetVisitingAndRoomString() {
        assertEquals("", myRoom.toRoomString());
        myRoom.setVisiting("G");
        assertEquals("G", myRoom.toRoomString());
    }

    /**
     * Tests the room occupant string representation when a player visits the room
     * and collects an item.
     */
    @Test
    public void testRoomOccupantWhenPlayerCollectsItem() {
        myRoom.setRoomOccupant("H");
        assertEquals("H", myRoom.toRoomString());
        myRoom.setVisiting("G");
        assertEquals("H G", myRoom.toRoomString());
        myRoom.setMyEmptyRoom(true);
        myRoom.setVisited();
        assertEquals("", myRoom.toRoomString());
    }

    /**
     * Tests the room occupant string representation when a player visits the room
     * but does not collect the item.
     */
    @Test
    public void testRoomOccupantWhenPlayerDoesNotCollectItem() {
        myRoom.setRoomOccupant("H");
        myRoom.setVisiting("G");
        assertEquals("H G", myRoom.toRoomString());
        myRoom.setMyEmptyRoom(false);
        myRoom.setVisited();
        assertEquals("H", myRoom.toRoomString());
    }
}
