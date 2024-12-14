/*
 * TCSS 360 - Dungeon Adventure
 */
package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * The class responsible for a room's condition.
 *
 * @author George Njane
 * @version 1.0
 */
public final class Room implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = 7493688377577152568L;

    /** The walls around this room. */
    private final Wall myRoomWalls;

    /** The String representation of the item inside this room. */
    private String myItem;

    /** The String representation of whether this room has been visited. */
    private String myStatus;

    /** The visit status. */
    private boolean myVisitStatus;

    /** Boolean representation of whether this room is empty. */
    private boolean myEmptyRoom;

    /**
     * Creates a Room with its Walls.
     * @param theRoomWalls the walls around this room.
     */
    private Room(final Wall theRoomWalls) {
        myRoomWalls = theRoomWalls;
        myVisitStatus = false;
        myItem = "";
        myStatus = "";
        myEmptyRoom = false;
    }

    /**
     * Creates a default room.
     */
    public static Room defualtRoom() {
        Wall roomWalls = new Wall(/*North*/ WallType.HORIZONTAL_WALL, /*South*/ WallType.HORIZONTAL_WALL,
                /*East*/ WallType.VERTICAL_WALL, /*West*/WallType.VERTICAL_WALL);
        return new Room(roomWalls);
    }

    /**
     * Turns this room into an empty room.
     * @param theEmptyMyRoom true if this room is going to be empty
     */
    public void setMyEmptyRoom(final boolean theEmptyMyRoom) {
        myEmptyRoom = theEmptyMyRoom;
    }

    /**
     * @return return whether this room is empty or not
     */
    public boolean getEmptyRoom() {
        return myEmptyRoom;
    }

    /**
     * @return the walls around this room.
     */
    public Wall getRoomWalls() {
        return myRoomWalls;
    }

    /**
     * The player is currently visiting this room.
     * @param thePlayer the name of the player
     */
    public void setVisiting(final String thePlayer) {
        StringBuilder str = new StringBuilder();
        str.append(myItem);
        str.append(" ");
        str.append(thePlayer);
        myStatus = (myItem.equals("") ? thePlayer : str.toString());
    }

    /**
     * Clears the item in the room if the room is visited.
     */
    public void setVisited() {
        if (myEmptyRoom) {
            myStatus = "";
            myItem = "";
        } else {
            myStatus = myItem;
        }
    }

    /**
     * Returns the item in the room. (e.g. Monster, Health, Pillar)
     *
     * @param theItem the item that is being added to the room, pillar, potion, exit, or monster
     */
    public void setRoomOccupant(final String theItem) {
        myItem = theItem;
        myStatus = theItem;
    }

    /**
     * Returns the item in the room. (e.g. Monster, Health, Pillar)
     * @return the item stored in the room.
     */
    public String getRoomOccupant() {
        return myItem;
    }

    /**
     * @param theVisitStatus whether the room had been visited
     */
    protected void setVisitStatus(final boolean theVisitStatus) {
        myVisitStatus = theVisitStatus;
    }

    /**
     * @return the visit status of the room.
     */
    protected boolean getVisitStatus() {
        return myVisitStatus;
    }

    /**
     * @return the wall type of the room north of this room.
     */
    public WallType getNorthWall() {
        return myRoomWalls.getNorthWall();
    }

    /**
     * Sets the wall type of the room north of this room.
     * @param theNorthWall the wall type
     */
    public void setNorthWall(final WallType theNorthWall) {
        myRoomWalls.setNorthWall(theNorthWall);
    }

    /**
     * @return the wall type of the room south of this room.
     */
    public WallType getSouthWall() {
        return myRoomWalls.getSouthWall();
    }

    /**
     * Sets the wall type of the room south of this room.
     * @param theSouthWall the wall type
     */
    public void setSouthWall(final WallType theSouthWall) {
        myRoomWalls.setSouthWall(theSouthWall);
    }

    /**
     * @return the wall type of the room east of this room.
     */
    public WallType getEastWall() {
        return myRoomWalls.getEastWall();
    }

    /**
     * Sets the wall type of the room east of this room.
     * @param theEastWall the wall type
     */
    public void setEastWall(final WallType theEastWall) {
        myRoomWalls.setEastWall(theEastWall);
    }

    /**
     * The west side wall.
     *
     * @return the wall type of the room west of this room.
     */
    public WallType getWestWall() {
        return myRoomWalls.getWestWall();
    }

    /**
     * Sets the wall type of the room west of this room.
     *
     * @param theWestWall the wall type
     */
    public void setWestWall(final WallType theWestWall) {
        myRoomWalls.setWestWall(theWestWall);
    }

    /**
     * toString used for debugging.
     */
    public String toRoomString() {
        return myStatus;
    }

    /**
     *
     */
    public String roomWallToString() {
        StringBuilder str = new StringBuilder();
        str.append(myRoomWalls.getNorthWall().getWallSymbol());
        str.append("\n");
        str.append(myRoomWalls.getEastWall().getWallSymbol());
        if (myItem.equals("")) {
            str.append(" ");
        } else {
            str.append(myItem);
        }
        str.append(myRoomWalls.getWestWall().getWallSymbol());
        str.append("\n");
        str.append(myRoomWalls.getSouthWall().getWallSymbol());

        return str.toString();
    }
}