package test;

import static org.junit.Assert.assertEquals;
import Model.Direction;
import Model.MazeGenerator;
import Model.Room;
import Model.WallType;
import org.junit.Before;
import org.junit.Test;

public class MazeGeneratorTest {
    private MazeGenerator myMaze;

    @Before
    public void setUp() {
        myMaze = new MazeGenerator(6,6);
    }
    @Test
    public void testOpenDoor() {
        Room room1 = Room.defualtRoom();
        Room room2 = Room.defualtRoom();
        myMaze.openDoor(room1, room2, Direction.NORTH);

        assertEquals(WallType.HORIZONTAL_DOOR, room1.getNorthWall());
        assertEquals(WallType.HORIZONTAL_DOOR, room2.getSouthWall());
    }
    @Test
    public void testMyRow() {
        assertEquals(6, myMaze.getRows());
    }
    @Test
    public void testMyCol() {
        assertEquals(6, myMaze.getCol());
    }
    @Test
    public void testPillarsPlacement() {
        int abstractionCount = 0;
        int encapsulationCount = 0;
        int inheritanceCount = 0;
        int polymorphismCount = 0;

        // Traverse the maze and count occurrences of each pillar
        for (int r = 0; r < myMaze.getMaze().length; r++) {
            for (int c = 0; c < myMaze.getMaze()[r].length; c++) {
                String occupant = myMaze.getMaze()[r][c].getRoomOccupant();
                switch (occupant) {
                    case "A" -> abstractionCount++;
                    case "E" -> encapsulationCount++;
                    case "I" -> inheritanceCount++;
                    case "P" -> polymorphismCount++;
                }
            }
        }
        assertEquals(1, abstractionCount);
        assertEquals(1, encapsulationCount);
        assertEquals(1, inheritanceCount);
        assertEquals(1, polymorphismCount);
    }
}
