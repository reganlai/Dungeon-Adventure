package Model;/*
 * TCSS 360 - Dungeon Adventure
 */

/**
 * The class responsible for a room's condition.
 *
 * @author George Njane
 * @version 1.0
 */
public final class Room {
    /** */
    private final Wall myRoomWalls;
    /** */
    private String myItem;

    /** The visit status. */
    private boolean myVisitStatus;

    private Room(final Wall theRoomWalls) {
        myRoomWalls = theRoomWalls;
        myVisitStatus = false;
        myItem = " ";
    }
    public static Room defualtRoom() {
        Wall roomWalls = new Wall(/*North*/ WallType.HORIZONTAL_WALL, /*South*/ WallType.HORIZONTAL_WALL,
                /*East*/ WallType.VERTICAL_WALL, /*West*/WallType.VERTICAL_WALL);
        return new Room(roomWalls);
    }

    public Wall getRoomWalls() {
        return myRoomWalls;
    }

    public void setRoomOccupant(final String theItem) {
        myItem = theItem;
    }

    /**
     * Returns the item in the room. (e.g. Model.Monster, Health, Pillar)
     *
     * @return the item stored in the room.
     */
    public String getRoomOccupant() {
        return myItem;
    }

    public void setVisitStatus(final boolean theVisitStatus) {
        myVisitStatus = theVisitStatus;

    }
    public boolean getVisitStatus() {
        return myVisitStatus;
    }

    public WallType getNorthWall() {
        return myRoomWalls.getNorthWall();
    }

    public void setNorthWall(final WallType theNorthWall) {
        myRoomWalls.setNorthWall(theNorthWall);
    }

    public WallType getSouthWall() {
        return myRoomWalls.getSouthWall();
    }

    public void setSouthWall(final WallType theSouthWall) {
        myRoomWalls.setSouthWall(theSouthWall);
    }

    public WallType getEastWall() {
        return myRoomWalls.getEastWall();
    }

    public void setEastWall(final WallType theEastWall) {
        myRoomWalls.setEastWall(theEastWall);
    }
    public WallType getWestWall() {
        return myRoomWalls.getWestWall();
    }

    public void setWestWall(final WallType theWestWall) {
        myRoomWalls.setWestWall(theWestWall);
    }

    public String roomToString() {
        StringBuilder str = new StringBuilder();
        str.append(myRoomWalls.getNorthWall().getWallSymbol());
        str.append("\n");
        str.append(myRoomWalls.getEastWall().getWallSymbol());
        str.append(myItem);
        str.append(myRoomWalls.getWestWall().getWallSymbol());
        str.append("\n");
        str.append(myRoomWalls.getSouthWall().getWallSymbol());

        return str.toString();
    }
}