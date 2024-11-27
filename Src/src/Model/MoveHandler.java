package Model;

public final class MoveHandler {
    private final boolean mySuccess;
    private final Room myNewRoom;

    private MoveHandler(final boolean theSuccess, final Room theNewRoom) {
        mySuccess = theSuccess;
        myNewRoom = theNewRoom;
    }
    public boolean getSuccess() {
        return mySuccess;
    }
    public Room getMyNewRoom() {
        return myNewRoom;
    }

    /**
     *
     * @param theMaze The current room location before the move.
     * @param theDir The intended move direction from the current room.
     * @param theHero The hero/player.
     *
     * @return a MoveHandler object with the success status and
     *          the room moved to if applicable.
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
