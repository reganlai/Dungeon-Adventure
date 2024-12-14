/*
 * TCSS 360 - Dungeon Adventure
 */
package test;

import static org.junit.Assert.assertEquals;
import Model.Direction;
import Model.MazeGenerator;
import Model.Room;
import Model.WallType;
import org.junit.Before;
import org.junit.Test;
/**
 * Unit tests for the {@link Model.MazeGenerator} class.
 * This class ensures the correctness of maze generation and its associated functionalities,
 * such as managing walls between rooms, maze dimensions, and pillar placement.
 *
 * @author George Njane
 * @version 12.13.24
 */
public class MazeGeneratorTest {

    /** A Maze generator for testing purposes. */
    private MazeGenerator myMaze;

    /**
     * Initializes a {@link MazeGenerator} instance with a 6x6 maze before each test.
     */
    @Before
    public void setUp() {
        myMaze = new MazeGenerator(6, 6);
    }

    /**
     * Tests the {@link MazeGenerator#openDoor(Room, Room, Direction)} method
     * to ensure that opening a door between two rooms correctly updates their wall types.
     */
    @Test
    public void testOpenDoor() {
        Room room1 = Room.defualtRoom();
        Room room2 = Room.defualtRoom();
        myMaze.openDoor(room1, room2, Direction.NORTH);

        assertEquals(WallType.HORIZONTAL_DOOR, room1.getNorthWall());
        assertEquals(WallType.HORIZONTAL_DOOR, room2.getSouthWall());
    }

    /**
     * Tests that the number of rows in the maze is correctly set and retrievable.
     */
    @Test
    public void testMyRow() {
        assertEquals(6, myMaze.getRows());
    }

    /**
     * Tests that the number of columns in the maze is correctly set and retrievable.
     */
    @Test
    public void testMyCol() {
        assertEquals(6, myMaze.getCol());
    }

    /**
     * Tests the correct placement of the four pillars in the maze.
     * <p>
     * The pillars represent core OOP principles:
     * <ul>
     *   <li>"A" for Abstraction</li>
     *   <li>"E" for Encapsulation</li>
     *   <li>"I" for Inheritance</li>
     *   <li>"P" for Polymorphism</li>
     * </ul>
     * <p>Each pillar should appear exactly once in the maze.
     */
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