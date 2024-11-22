package test;

import static org.junit.Assert.*;

import Model.Direction;
import Model.MazeGenerator;
import Model.Room;
import Model.WallType;
import org.junit.Test;

public class MazeGeneratorTest {
    @Test
    public void testOpenDoor() {
        MazeGenerator maze = new MazeGenerator(6,6);
        Room room1 = Room.defualtRoom();
        Room room2 = Room.defualtRoom();

        maze.openDoor(room1, room2, Direction.NORTH);


        assertEquals(WallType.HORIZONTAL_DOOR, room1.getNorthWall());


        assertEquals(WallType.HORIZONTAL_DOOR, room2.getSouthWall());


    }
}
