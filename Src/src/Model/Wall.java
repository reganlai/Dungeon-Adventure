package Model;/*
 * TCSS 360 - Dungeon Adventure
 */

/**
 * A class representing the four walls of a room and their current state.
 *
 * @author George Njane
 * @version 1.0
 */
public final class Wall {
    /** The north wall of the room. */
    private WallType myNorthWall;

    /** The south wall of the room. */
    private WallType mySouthWall;

    /** The east wall of the room. */
    private WallType myEastWall;

    /** The west wall of the room. */
    private WallType myWestWall;

    /**
     * Constructs a new Model.Wall with specified states for each direction.
     *
     * @param theNorthWall the state of the north wall
     * @param theSouthWall the state of the south wall
     * @param theEastWall the state of the east wall
     * @param theWestWall the state of the west wall
     */
    public Wall(final WallType theNorthWall, final WallType theSouthWall,
                final WallType theEastWall, final WallType theWestWall) {
        myNorthWall = theNorthWall;
        mySouthWall = theSouthWall;
        myEastWall = theEastWall;
        myWestWall = theWestWall;
    }
    /**
     * Returns the state of the north wall.
     *
     * @return the state of the north wall
     */
    protected WallType getNorthWall() {
        return myNorthWall;
    }
    /**
     * Sets the state of the north wall.
     *
     * @param theNorthWall the new state for the north wall
     */
    protected void setNorthWall(final WallType theNorthWall) {
        myNorthWall = theNorthWall;
    }
    /**
     * Returns the state of the south wall.
     *
     * @return the state of the south wall
     */
    protected WallType getSouthWall() {
        return mySouthWall;
    }
    /**
     * Sets the state of the south wall.
     *
     * @param theSouthWall the new state for the south wall
     */
    protected void setSouthWall(final WallType theSouthWall) {
        mySouthWall = theSouthWall;
    }
    /**
     * Returns the state of the east wall.
     *
     * @return the state of the east wall
     */
    protected WallType getEastWall() {
        return myEastWall;
    }
    /**
     * Sets the state of the east wall.
     *
     * @param theEastWall the new state for the east wall
     */
    protected void setEastWall(final WallType theEastWall) {
        myEastWall = theEastWall;
    }
    /**
     * Returns the state of the west wall.
     *
     * @return the state of the west wall
     */
    protected WallType getWestWall() {
        return myWestWall;
    }
    /**
     * Sets the state of the west wall.
     *
     * @param theWestWall the new state for the west wall
     */
    protected void setWestWall(final WallType theWestWall) {
        myWestWall = theWestWall;
    }
}
