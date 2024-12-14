package Model;/*
 * TCSS 360 - Dungeon Adventure
 */

/**
 * An enumeration for the available wall types in a room.
 *
 * @author George Njane
 * @version 1.0
 */
public enum WallType {

    /**
     * String representation of a horizontal wall.
     */
    HORIZONTAL_WALL("***"),

    /**
     * String representation of a horizontal door.
     */
    HORIZONTAL_DOOR("*_*"),

    /**
     * String representation of a vertical wall.
     */
    VERTICAL_WALL("*"),

    /**
     * String representation of a vertical door.
     */
    VERTICAL_DOOR("|");

    private final String myWallSymbol;

    /**
     * Constructs a new WallTyoe with the specified string.
     *
     * @param theWallSymbol The string.
     */
    WallType(final String theWallSymbol) {
        myWallSymbol = theWallSymbol;
    }

    /**
     * The symbol representing the type of wall.
     *
     * @return a string symbol of the wall.
     */
    protected String getWallSymbol() {
        return myWallSymbol;
    }
}
