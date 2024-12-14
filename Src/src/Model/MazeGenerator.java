package Model;/*
 * TCSS 360 - Dungeon Adventure
 */
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * The class responsible for the creation of the maze as well as
 * each individual room in the maze.
 *
 * @author George Njane
 * @version 1.0
 */
public final class MazeGenerator implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = 7567907344024427690L;

    /** The percent of the item is going to take up in the dungeon(in squares). */
    private static final int ITEM_PERCENTAGE = 10;

    /** A "random" object. */
    private final Random myRandom = new Random();

    /** How many rooms are occupied. */
    private final Map<Integer, Set<Integer>> myOccupiedRooms;

    /** The row that the user spawned in. */
    private final int mySpawnInRow;

    /** The column that the user spawned in. */
    private final int mySpawnInCol;

    /** The percentage of items in the dungeon. */
    private final int myItemsPercent;

    /** The dungeon as a 2d array*/
    private Room myMaze[][];

    /** How many items have been added to the dungeon. */
    private int myAddedItemsCount;

    /** How many rows this dungeon have. */
    private int myRows;

    /** How many columns this dungeon have. */
    private int myCols;

    /**
     * Creates a maze.
     * @param theCols the number of columns this dungeon has
     * @param theRows the number of rows this dungeon has
     */
    public MazeGenerator(final int theRows, final int theCols) {
        myAddedItemsCount = 0;
        myOccupiedRooms = new HashMap<>();
        myRows = theRows;
        myCols = theCols;
        mySpawnInRow = myRandom.nextInt(myRows);
        mySpawnInCol = myRandom.nextInt(myCols);
        myItemsPercent = (int) Math.ceil((double) (theRows * theCols) / ITEM_PERCENTAGE);
        setMaze(theRows, theCols);
        initMaze();
    }

    /**
     * @return number of rows of this dungeon
     */
    public int getRows() {
        return myRows;
    }

    /**
     * @return number of columns of this dungeon
     */
    public int getCol() {
        return myCols;
    }

    /**
     * @return the dungeon as a 2d array of rooms
     */
    public Room[][] getMaze() {
        return myMaze;
    }

    /**
     * Sets the dimension of the maze determined by the difficulty level
     * @param theRows the number of rows this dungeon have
     * @param theCols he number of columns this dungeon have
     */
    private void setMaze(final int theRows, final int theCols) {
        if (theRows < 1 || theCols < 0) {
            throw new IllegalArgumentException("The maze dimensions are incorrect.");
        }
        myMaze = new Room[theRows][theCols];
    }

    /**
     * Initializes the maze with room object.
     * The rooms borders are initially set to walls.
     */
    private void initMaze() {
        for (int r = 0; r < myRows; r++) {
            for (int c = 0; c < myCols; c++) {

                myMaze[r][c] = Room.defualtRoom();

            }
        }
        generateMaze();
    }

    /**
     * Returns the hero's spawn in location.
     * This is also the entrance to the maze.
     *
     * @return The maze spawn-in row
     */
    public int getMySpawnInRow() {
        return mySpawnInRow;
    }

    /**
     * Returns the hero's spawn in location.
     * This is also the entrance to the maze.
     *
     * @return The maze spawn-in column
     */
    public int getMySpawnInCol() {
        return mySpawnInCol;
    }

    /**
     * Generates the maze using a depth-first search algorithm.
     * Randomly places the entrance and randomly chooses an exit
     * that's on the border of the maze. Adds the rooms items.
     */
    private void generateMaze() {
        myMaze[mySpawnInRow][mySpawnInCol].setRoomOccupant("i"); //Entrance
        addValueToMap(mySpawnInRow, mySpawnInCol);
        myMaze[mySpawnInRow][mySpawnInCol].setVisitStatus(true);

        dfs(mySpawnInRow, mySpawnInCol);


        int exitRow = 0, exitCol = 0;
        boolean chooseRow = myRandom.nextBoolean();
        if (chooseRow) { // Chooses a room for the exit that is on the border of the maze.
            exitRow = myRandom.nextInt(myRows);
            // Ensure exit is not in an already occupied spot in this row
            while (myOccupiedRooms.get(exitRow) != null && myOccupiedRooms.get(exitRow).contains(exitCol)) {
                exitRow = myRandom.nextInt(myRows); // Randomly choose again until valid spot
            }
            myMaze[exitRow][exitCol].setRoomOccupant("O");  // Set exit at random spot
        } else {
            exitCol = myRandom.nextInt(myCols);
            // Check and avoid occupied spots
            while (myOccupiedRooms.get(exitRow) != null && myOccupiedRooms.get(exitRow).contains(exitCol)) {
                exitCol = myRandom.nextInt(myCols); // Randomly choose again until valid spot
            }
            myMaze[exitRow][exitCol].setRoomOccupant("O");  // Set exit at random spot
        }
        addValueToMap(exitRow, exitCol);

        setRoomOccupant("M", myItemsPercent); // Add the items of the room (e.g. Monster, Health, Pillar) randomly. One per room.
        setRoomOccupant("A", 1); // Abstraction
        setRoomOccupant("E", 1); //Encapsulation
        setRoomOccupant("I", 1); // Inheritance
        setRoomOccupant("P", 1); //Polymorphism
        setRoomOccupant("H", myItemsPercent); // Health potion
    }

    /**
     * The algorithm uses depth-first search to create a path throughout the maze.
     * This allows the maze to have access to every room. It creates doors when needed
     * and avoids creating multiple doors within a room unless needed.
     * It randomly chooses a direction from a list, undergoes through
     * various checks to see if valid then decides whether to proceed.
     * It backtracks if none of the directions are valid.
     *
     * @param theRow the current row of the room.
     * @param theCol the current column of the room.
     */
    private void dfs(final int theRow, final int theCol) {
        final int row = theRow;
        final int col = theCol;

        List<Direction> directionList = new ArrayList<>();
        directionList.add(Direction.NORTH);
        directionList.add(Direction.SOUTH);
        directionList.add(Direction.EAST);
        directionList.add(Direction.WEST);

        Collections.shuffle(directionList);

        for (Direction dir: directionList) {
            int newRow = row, newCol = col;

            switch (dir) {
                case NORTH:
                    newRow--;
                    break;
                case SOUTH:
                    newRow++;
                    break;
                case WEST:
                    newCol--;
                    break;
                default:
                    newCol++;
            }
            if (isInBound(newRow, newCol) && !myMaze[newRow][newCol].getVisitStatus()) {
                //System.err.print(dir + " ");
                myMaze[newRow][newCol].setVisitStatus(true);
                openDoor(myMaze[row][col], myMaze[newRow][newCol], dir);
                dfs(newRow, newCol);
            }

        }
    }

    /**
     * Checks if the position ([row][col]) is in bound.
     *
     * @param theRow a row for the given position.
     * @param theCol a column for the given position.
     * @return true if the given position is in bound, false otherwise.
     */
    private boolean isInBound(final int theRow, final int theCol) {
        return theRow >= 0 && theRow < myMaze.length
                && theCol >= 0 && theCol < myMaze[0].length;
    }

    /**
     * Creates a door between the current room and the next room creating a path
     * between the two rooms.
     * @param theCurrentRoom The current room.
     * @param theNextRoom The next room to create a path to.
     * @param theCurrentDirection The direction from the current room to the next room.
     */
    public void openDoor(final Room theCurrentRoom,
                          final Room theNextRoom, final Direction theCurrentDirection) {

        switch (theCurrentDirection) {
            case NORTH:

                theCurrentRoom.setNorthWall(WallType.HORIZONTAL_DOOR);
                theNextRoom.setSouthWall(WallType.HORIZONTAL_DOOR);
                break;
            case SOUTH:
                theCurrentRoom.setSouthWall(WallType.HORIZONTAL_DOOR);
                theNextRoom.setNorthWall(WallType.HORIZONTAL_DOOR);
                break;
            case WEST:
                theCurrentRoom.setWestWall(WallType.VERTICAL_DOOR);
                theNextRoom.setEastWall(WallType.VERTICAL_DOOR);
                break;
            default:
                theCurrentRoom.setEastWall(WallType.VERTICAL_DOOR);
                theNextRoom.setWestWall(WallType.VERTICAL_DOOR);
        }

    }

    /**
     * Adds theCount amount of items to random cells.
     * Uses a map to keep track of rooms that already have items.
     *
     * @param theItem the item to add to a room.
     * @param theCount the quantity of items to add.
     */
    private void setRoomOccupant(final String theItem, final int theCount) {
        int itemsCount = 0;
        while (itemsCount < theCount) {
            final int row = myRandom.nextInt(myRows);
            final int col = myRandom.nextInt(myCols);

            if (myOccupiedRooms.get(row) != null) {
                if(!myOccupiedRooms.get(row).contains(col) ) {
                    addValueToMap(row, col);
                    myMaze[row][col].setRoomOccupant(theItem);
                    itemsCount++;
                }
            } else {
                addValueToMap(row, col);
                myMaze[row][col].setRoomOccupant(theItem);
                myAddedItemsCount++;
                itemsCount++;
            }
        }

    }

    /**
     * Uses a map key Integer with a Set of Integers as value.
     * Adds the given row to a map as a key and maps it to a set.
     * If there's a pre-existing set, it adds the column number to the set.
     * Other-wise it creates a new set and adds the column number to the new set.
     *
     * @param theRow the row to add to the myOccupiedRooms map.
     * @param theCol the Column to add as the value in a set mapped to the row.
     */
    private void addValueToMap(final int theRow, final int theCol) {
        Set<Integer> set = myOccupiedRooms.getOrDefault(theRow, new HashSet<>());
        set.add(theCol);
        myOccupiedRooms.put(theRow, set);
    }

    /**
     * The hero will only move if moving in that direction is in bound of
     * the maze, and if moving in that direction doesn't hit a wall.
     * Thus, this function changes the current X or Y by moving in the
     * given direction when applicable, and returns 1 if the character
     * can move and has moved, otherwise it returns 0.
     *
     * @param theDir the intended moving direction.
     * @param theHero the current hero.
     * @return 1 if character moved, 0 otherwise
     */
    public MoveHandler move(final Direction theDir, final DungeonCharacter theHero) {
        return MoveHandler.move(myMaze, theDir, theHero);
    }

    /**
     * The room class has a getRoomOccupant class that can also
     * return the item currently in a room.
     *
     * @param theHero the current hero object.
     * @return A string representation of the room's occupant.
     */
    private String whatInTheRoom(DungeonCharacter theHero) {
        return myMaze[theHero.getMyX()][theHero.getMyY()].toString();
    }

    /**
     * A toString representation of the 2-Dimensional graphical maze.
     *
     * @return a string representing the 2D graphical
     *          representation of the maze.
     */
    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        for (int row = 0; row < myRows; row++) {
            for (int col = 0; col < myCols; col++) {
                strBuilder.append(myMaze[row][col].getNorthWall().getWallSymbol()).append(" ");
            }
            strBuilder.append("\n");
            for (int col = 0; col < myCols; col++) {
                strBuilder.append(myMaze[row][col].getWestWall().getWallSymbol());
                strBuilder.append(myMaze[row][col].getRoomOccupant() == "" ? " " : myMaze[row][col].getRoomOccupant());
                strBuilder.append(myMaze[row][col].getEastWall().getWallSymbol()).append(" ");
            }
            strBuilder.append("\n");
            for (int col = 0; col < myCols; col++) {
                strBuilder.append(myMaze[row][col].getSouthWall().getWallSymbol()).append(" ");
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }
}
