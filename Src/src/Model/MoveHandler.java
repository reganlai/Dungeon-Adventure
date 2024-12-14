/*
 * TCSS 360 - Dungeon Adventure
 */
package Model;
/**
 * The {@code MoveHandler} class handles the movement logic of a hero/player
 * within a maze-like dungeon. It determines whether a move in a specified
 * direction is possible and updates the hero's position and the room state accordingly.
 * This class is immutable and ensures thread-safety by synchronizing the static
 * move method.
 *
 * @author George Njane
 * @version 1.0
 */
public final class MoveHandler {

    /** Indicates whether the move was successful. */
    private final boolean mySuccess;

    /** The room the hero moved into, if the move was successful. */
    private final Room myNewRoom;

    /**
     * Initializes a new {@code MoveHandler} object with the move status and the new room.
     *
     * @param theSuccess true if the move was successful; false otherwise.
     * @param theNewRoom the room the hero moved into; null if the move was unsuccessful.
     */
    private MoveHandler(final boolean theSuccess, final Room theNewRoom) {
        mySuccess = theSuccess;
        myNewRoom = theNewRoom;
    }

    /**
     * Retrieves the success status of the move.
     *
     * @return true if the move was successful; false otherwise.
     */
    public boolean getSuccess() {
        return mySuccess;
    }

    /**
     * Retrieves the room the hero moved into.
     *
     * @return the new {@link Room} if the move was successful; {@code null} otherwise.
     */
    public Room getMyNewRoom() {
        return myNewRoom;
    }

    /**
     * Handles the hero's move within the maze.
     * Validates the hero's ability to move in the specified direction based on the
     * current room's wall configuration and maze boundaries. Updates the hero's
     * position and room visitation status accordingly.
     *
     * @param theMaze the maze represented as a 2D array of {@link Room} objects.
     * @param theDir the {@link Direction} in which the hero intends to move.
     * @param theHero the {@link DungeonCharacter} representing the hero/player.
     *
     * @return a {@code MoveHandler} object containing the move's success status and
     *         the room moved into (if applicable).
     */
    public synchronized static MoveHandler move(final Room[][] theMaze, final Direction theDir,
                                                    final DungeonCharacter theHero) {
        boolean result = false;
        int moved = 0;
        final Room currentRoom = theMaze[theHero.getMyY()][theHero.getMyX()];
        currentRoom.setVisited();
        Room theNewRoom = null;
        switch (theDir) {
            case NORTH:
                System.out.println("Hero at (" + theHero.getMyY() + ", " + theHero.getMyX() + ")");
                System.out.println("North Wall: " + theMaze[theHero.getMyY()][theHero.getMyX()].getNorthWall());
                if (theMaze[theHero.getMyY()][theHero.getMyX()].getNorthWall() != WallType.HORIZONTAL_WALL &&
                        theHero.getMyY() - 1 >= 0) {
                    moved = theHero.getMyY() - 1;
                    //currentRoom.setVisited(empty);
                    theNewRoom = theMaze[moved][theHero.getMyX()];
                    theNewRoom.setVisiting(theHero.getMyName().substring(0,1));
                    theHero.setMyY(moved);
                    result = true;

                } else {
                    System.out.println("Cannot move NORTH. Wall type: " +
                            theMaze[theHero.getMyY()][theHero.getMyX()].getNorthWall());
                }
                break;
            case SOUTH:
                System.out.println("Hero at (" + theHero.getMyY() + ", " + theHero.getMyX() + ")");
                System.out.println("South Wall: " + theMaze[theHero.getMyY()][theHero.getMyX()].getSouthWall());
                if (theHero.getMyY() + 1 < theMaze.length &&
                        theMaze[theHero.getMyY()][theHero.getMyX()].getSouthWall() != WallType.HORIZONTAL_WALL) {
                    moved = theHero.getMyY() + 1;
                    theNewRoom = theMaze[moved][theHero.getMyX()];
                    theNewRoom.setVisiting(theHero.getMyName().substring(0,1));
                    theHero.setMyY(moved);
                    result = true;
                } else {
                    System.out.println("Cannot move South. Wall type: " +
                            theMaze[theHero.getMyY()][theHero.getMyX()].getSouthWall());
                }
                break;
            case EAST:
                System.out.println("Hero at (" + theHero.getMyY() + ", " + theHero.getMyX() + ")");
                System.out.println("East Wall: " + theMaze[theHero.getMyY()][theHero.getMyX()].getEastWall());
                if (theHero.getMyX() + 1 < theMaze[0].length &&
                        theMaze[theHero.getMyY()][theHero.getMyX()].getEastWall() != WallType.VERTICAL_WALL) {
                    moved = theHero.getMyX() + 1;
                    theNewRoom = theMaze[theHero.getMyY()][moved];
                    theNewRoom.setVisiting(theHero.getMyName().substring(0,1));
                    theHero.setMyX(moved);
                    result = true;
                } else {
                    System.out.println("Cannot move East. Wall type: " +
                            theMaze[theHero.getMyY()][theHero.getMyX()].getEastWall());
                }
                break;
            case WEST:
                System.out.println("Hero at (" + theHero.getMyY() + ", " + theHero.getMyX() + ")");
                System.out.println("West Wall: " + theMaze[theHero.getMyY()][theHero.getMyX()].getWestWall());
                if (theHero.getMyX() - 1 >= 0 &&
                        theMaze[theHero.getMyY()][theHero.getMyX()].getWestWall() != WallType.VERTICAL_WALL) {
                    moved = theHero.getMyX() - 1;
                    theNewRoom = theMaze[theHero.getMyY()][moved];
                    theNewRoom.setVisiting(theHero.getMyName().substring(0,1));
                    theHero.setMyX(moved);
                    result = true;
                } else {
                    System.out.println("Cannot move West. Wall type: " +
                            theMaze[theHero.getMyY()][theHero.getMyX()].getWestWall());
                }
                break;
        }
        System.out.println("Hero at (" + theHero.getMyY() + ", " + theHero.getMyX() + ")");

        return new MoveHandler(result, theNewRoom);
    }
}
